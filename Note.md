======================================== CSRF PROTECTIONS ========================================
1. Mặc định: Khi chúng ta enable CSRF protection(default) thì khi đó Spring Security sẽ bảo vệ tất cả các POST Method được gửi từ client tới cho Server(Client sẽ nhận được 403 forbidden). Bất kể là client có giữ JSESSIONID hay không. Nên do đó, nếu trong contact.jsp của chúng ta không lưu csrf token thì khi đó chắc chắn chúng ta không thể Submit form tới cho client. Vì vậy, chúng ta phải tạo một nhận vào một csrf token ở contact form, từ đó mới có thể gửi được Request tới cho client.


======================================== LOGGING OUT ========================================
- Khi chúng ta cho phép mặc định CSRF thì khi đó LogoutFilter của Spring Security sẽ chỉ xử lý các yêu cầu /logout với POST Method. Bởi vì:
  - Hiểu đơn giản thì, nếu như cho phép /logout bằng GET Method thì người dùng xấu chỉ cần có JSESSIONID để logout trang web của chúng ta. Điều này dẫn tới các hệ quả không mong muốn về sau và cũng không được khuyến khích sử dụng.
  - Nếu LogoutFilter sử dụng POST Method thì khi đó, nó mong muốn người dùng cung cấp JSESSIONID + csrf token để kiểm tra tính chính xác của cả 2 token này. Nếu cả 2 token này đúng thì hệ thống mới cho phép người dùng đăng xuất. Và vì vậy nên người dùng xấu sẽ không thể dễ dàng đăng xuất người dùng khởi ứng dụng được.
 
- "Logging Out
It is important to require CSRF for log out requests to protect against forging logout attempts. If CSRF protection is enabled (the default), Spring Security’s LogoutFilter will only process HTTP POST requests. This ensures that logging out requires a CSRF token and that a malicious user cannot forcibly log your users out.

The easiest approach is to use a form to log the user out. If you really want a link, you can use JavaScript to have the link perform a POST (maybe on a hidden form). For browsers with JavaScript that is disabled, you can optionally have the link take the user to a log out confirmation page that performs the POST.

If you really want to use HTTP GET with logout, you can do so. However, remember that this is generally not recommended. For example, the following logs out when the /logout URL is requested with any HTTP method:"

==> GIẢI PHÁP: Sử dụng Form với method="post" và action="logout". Bên trong chứa <security:csrfInput /> để tạo csrf token. Từ đó, Spring security' LogoutFilter có thể xác thực yêu cầu của người dùng hợp lệ thông qua JSESSIONID và csrf token.

======================================== TỔNG KẾT VỀ CSRF PROTECTIONS ========================================
1. Trường hợp 1: Chưa bật CSRF.
- Trong trường hợp này, thì Spring Security sẽ không xử lý bất kì HTTP METHODS Request nào từ phía Client. Các URL cần authenticated hoặc permitAll() vẫn sẽ hoạt động như thông thường.
==> Điều này sẽ dẫn tới, chỉ cần người dùng xấu họ lấy được JSESSIONID là họ có thể truy cập và thay đổi dữ liệu của người dùng dễ dàng.
2. Trường hợp 2: Đã bật CSRF.
- Trong trường hợp này, thì Spring Security sẽ xử lý tất cả các HTTP POST, PUT and DELETE Request từ Client cho dù đó có là Login Form đi nữa.
- Khi đó, nếu như trong Form không duy trì một CSRF TOKEN hợp lệ và JSESSIONID hợp lệ thì tất cả các request này sẽ trả về 403 Forbidden hết.
- Lưu ý thêm trường hợp LOGGING OUT ở phía bên trên.


======================================== SPRING DATA JPA ========================================
1. Persistence Repository hay Persistence Layer là lớp mà nó thực hiện việc tương tác với cơ sở dữ liệu. Sau này chúng ta sẽ phải học thêm phần Caching.
2. Về tổng quan thì mọi subproject của Spring Data đều dựa trên Repository interface(contract)
2. Spring data jpa là một subproject của Spring Data mà trong đó nó triển khai JPA Specification và triển khai luôn cả ORM dựa trên việc sử dụng Hibernate là framework chính hoạt động với nhiệm vụ thực hiện ORM. 
3. Bên cạnh đó, điểm mạnh và quan trọng nhất của Spring Data JPA đó là việc có thêm tính năng Derived Query Method giúp cho developer có thể thực hiện truy vấn đối tượng thông qua Custom các Query Method - Là method tạo ra từ việc đặt tên dựa vào Query như findById, findByUsername....


======================================== CREATE NEW USER ========================================
1. Do trong đối tượng User chúng ta có sử dụng Entity annotation nên khi chúng ta thực hiện phương thức save() của Data JPA thì khi đó đối tượng này sẽ được validation lại một lần nữa. Điều đó dẫn tới việc password != confirmPassword mà User họ cung cấp trước đó. Điều này sẽ dẫn tới lỗi khi thực hiện kiểm tra xem password - confirmPassword có khớp hay không.
2. Chúng ta đã sử dụng giải pháp tạm thời là dùng thêm câu lệnh "passwordEncoder.matchs((String)fieldMatchValue, (String)fieldValue)" để khắc phục vấn đề này. Xem lại ở commit phía trước.
3. Ngoài ra chúng ta có thể sử dụng: spring.jpa

======================================== @Valid ANNOTATION. ========================================
1. _@Valid_ chỉ sử dụng cho _@Entity_ class.
2. Nếu gặp class chỉ là @Data thì chúng ta phải sử dụng _Validator_ của _jakartar.validation_. Và thực hiện validate ở bên trong phương thức chứ không thực hiện thêm bất kì annotation nào giống như khi làm với @Valid. (Xem chi tiết cách sử dụng Validator ở _ProfileController.java_).
