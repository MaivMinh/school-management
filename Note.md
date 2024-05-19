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