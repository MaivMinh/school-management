======================================== KIẾN THỨC CỰC KÌ QUAN TRỌNG VỀ MỐI QUAN HỆ @MANYTOONE. ========================================
1. _@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, optional = false)
   @JoinColumn(name = "lecturer", referencedColumnName = "userId", nullable = false)
   private User lecturer;_ 
   - Bên trên là cấu hình của mối quan hệ nhiều một giữa Course - User. Trong đó, @JoinColumn(name="lecture", referencedColumnName="userId", nullable=false) chúng ta sẽ giải thích cấu hình này như sau:
     + name="lecture" thể hiện cho việc là property _User lecture_ tương ứng với Course.lecture phía dưới CSDL của chúng ta.
     + Sau khi xác định được mối liên hệ giữa property trên Class với column dưới Table thì chúng ta cần xác định Column của Table cụ thể nào mà chúng ta đang tham chiếu tới.
     + referencedColumnName="userId" + User: Thuộc tính kiểu User của lecture thể hiện rằng Courses sẽ phải tham chiếu tới Class User và sau đó hãy tham chiếu tới property có tên là userId. Và từ đó, chúng ta thông qua userId và thấy rằng userId này liên kết với User.user_id phía dưới CSDL.

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
1. Do trong đối tượng User chúng ta có sử dụng Entity annotation nên khi chúng ta thực hiện phương thức save() của Data JPA thì khi đó đối tượng này sẽ được validation lại một lần nữa. Điều đó dẫn tới việc password != confirmPassword(_Xem cụ thể bên trong UserService.createNewUser()_) mà User họ cung cấp trước đó. Điều này sẽ dẫn tới lỗi khi thực hiện kiểm tra xem password - confirmPassword có khớp hay không.
2. Chúng ta đã sử dụng giải pháp tạm thời là dùng thêm câu lệnh "passwordEncoder.matchs((String)fieldMatchValue, (String)fieldValue)" để khắc phục vấn đề này. Xem lại ở commit phía trước.
3. Ngoài ra chúng ta có thể sử dụng: spring.jpa.properties.jakarta.persistence.validation.mode=none

======================================== @Valid ANNOTATION. ========================================
1. _@Valid_ chỉ sử dụng cho _@Entity_ class.
2. Nếu gặp class chỉ là @Data thì chúng ta phải sử dụng _Validator_ của _jakartar.validation_. Và thực hiện validate ở bên trong phương thức chứ không thực hiện thêm bất kì annotation nào giống như khi làm với @Valid. (Xem chi tiết cách sử dụng Validator ở _ProfileController.java_).

======================================== optional AND nullable in @ManyToOne. ========================================
1. Khi sử dụng optional=true, điều này có nghĩa là mối quan hệ giữa User và Class là mối quan hệ luôn tồn tại. Nếu như chúng ta tạo một Object User mà không liên kết chúng với bất kì Object Class nào lúc khởi tạo thì sẽ gây ra lỗi.
2. Sử dụng nullable=true là một lựa chọn gần giống với optional phía bên trên. Khi cho nullable=true thì khi đó khoá ngoại được tham chiêếu này sẽ có thể là NULL, khi đó Object User được tạo ra có thể không cần tham chiếu tới bất kì đối tượng Class nào khác.

======================================== KIẾN THỨC QUAN TRỌNG VỀ CascadeType.PERSIST. ========================================
1. Cho ví dụ về một mối quan hệ Many-to-Many như sau:
   courses.getUsers().add(user);
   user.getCourses().add(courses);
   courses = coursesService.save(courses);
2. Đoạn code trên có được trong _AdminController_ dòng 151 gì đó, thể hiện việc thêm một User vào Course và ngược lại. Đoạn code này và những đoạn phía dưới không hề chứa thêm bất kì thao tác save(user) nào. Vì sao lại như thế: 
   - Nguyên nhân là bởi vì CascadeType.PERSIST nó vừa có thể tạo một đối tượng mới nếu chúng được tạo mới hoàn toàn, hoặc nó có thể _UPDATE_ chinh đối tượng hiện có nếu chúng vừa cập nhật một giá trị mới nào ở trong fields của chúng. 
   - Ở đoạn code trên chúng ta có thể thấy rằng đối tượng _user_ đã cập nhật thêm một Course vào trong danh sách, nên đó trở thành đối tuợng vừa cập nhật mới các giá trị. Do đó, nên khi chúng ta save(courses) thì CascadeType.PERSIST cũng sẽ hoạt động để update lại giá trị cho user ở phía dưới _DATABASE_. Và vì thế chúng ta không cần có thêm đoạn code _userService.save(user)_ dư thừa này.


======================================== VÍ DỤ CHO THẤY SỰ KHÁC NHAU RÕ RỆT GIỮA FetchType.EAGER - FetchType.LAZY. ========================================
1. Ở _DashBoardController.displayDashboard()_ khi chúng ta sử dụng HttpSession để lưu một đối tượng vào Session thì lúc này nếu chúng ta sử dụng LAZY loading thì khi đó bên trong User của chúng ta sẽ chỉ chứa danh sách Course rỗng. Nguyên nhân là bởi vì chúng ta không có sử dụng User để fetch Course lên trước khi lưu vào Session nên khi đó chúng ta sẽ có danh sách Course rỗng.
2. Ngược lại, nếu sử dụng EAGER ngay từ đầu chúng ta sẽ có sẵn danh sách Course ngay từ khi tạo ra User, nên do đó, khi chúng ta lấy User từ trong Session ra thì sẽ có luôn danh sách Course.

======================================== CONSUMING ANOTHER REST SERVICE. ========================================
1. Nếu Service của chúng ta muốn call API tới một REST Service bên ngoài khác thì đây là các lựa chọn: 
   1. OpenFeign -> Spring Cloud.
   2. RestTemplate -> Blocking I/O.
   3. WebClient -> Non-Blocking I/O của Spring Webflux.
-> 1 và 3 được khuyến khích sử dụng, RestTemplate đã deprecated.

======================================== SPRING BOOT PROFILES. ========================================
1. Mục đích của Spring Boot Profiles là để sử dụng các configurations phù hợp đối với từng môi trường phát triển phù hợp như Dev, UAT, Prod.
2. Mặc định, _application.properties_ là file cấu hình luôn được load ở trong Spring Boot. Nhưng sau đó, chúng ta sẽ cung cấp các file properties khác nhau phụ thuộc vào môi trường mà chúng ta đang triển khai. Các thuộc tính sẽ override nếu tồn tại.
3. Chúng ta có thể sử dụng @Profile annotation để cấu hình các Bean có điều kiện. Có thể xem chi tiết tại _UsernamePasswordAuthProvider_ và _NonUsernamePasswordAuthProvider_. 


======================================== SỬ DỤNG CLOUDINARY TRONG VIỆC LƯU TRỮ HÌNH ẢNH. ========================================
1. Nếu muốn bảo mật và các hình ảnh không cần phải truy xuất nhiều thì nên lưu ảnh dưới dạng nhị phân rồi lưu thẳng vào DB. Nhưng cách làm này tốn nhiều thời gian xử lý hơn bởi vì phải tạo các Class cho việc xử lý convert qua lại. Hơn nữa, nếu sử dụng Cloud thì việc truy xuất nhiều như vậy sẽ gây hao phí băng thông.
2. Do đó, quyết định sử dụng Cloudinary bởi vì thứ nhất đó cũng là một công nghệ phổ biến hiện tại. Hơn nữa, việc chỉ phải lưu URL phía dưới Database và không phải Convert qua lại khiến cho Performance được cải thiện nhiều.
3. Quá trình làm việc với Cloudinary chỉ bao gồm các bước cơ bản sau:
   1. Tạo Cloudinary Bean (xem lại trong ProjectConfiguration), truyền vào cloud_name, api_key, api_secret.
   2. Tạo CloudinaryService có chức năng là uploadFile lên Server, sau khi thực thi xong thì hàm này sẽ trả về cho chúng ta URL.
   3. Lưu ý, vì Form sẽ trả về MultipartFile, chúng ta phải chuyển đổi nó thành File. Xem lại trong ProjectConfiguration.
   4. Cuối cùng là lưu URL vào Table users.

======================================== LƯU Ý QUAN TRỌNG KHI SỬ DỤNG SPRING DATA REST. ========================================
1. Thông thường, khi thực hiện request một object có @ToMany annotations, chúng ta thường sử dụng FetchType.LAZY để tránh làm giảm hiểu suất của ứng dụng.
2. Nhưng khi sử dụng SPRING DATA REST với @RestController thì nó sẽ Fetch toàn bộ data có liên quan cho dù đó có là LAZY hay EAGER. Vấn đề này là mặc định và gần như khó thay đổi. Nó bắt nguồn từ Jackson sẽ serializable toàn bộ data có liên quan. Việc Fetch toàn bộ dữ liệu như này sẽ dẫn tới infinite fetch hay còn được biết tới là _JSON infinite recursion Stackoverflow_. Do các đối tượng có quan hệ nhiều-nhiều, một-nhiều truy xuất ngược lại lẫn nhau. Có thể xem cụ thể ở class Course và class User.
3. Do đó, để khắc phục vấn đề này thì chúng ta thường áp dụng 2 giải pháp.
   1. Sử dụng @JsonManagedReference và @JsonBackReference. @JsonManagedReference nên được sử dụng ở bên định nghĩa @JoinTable.
   2. Sử dụng DTOs để trả về cho client. 


======================================== KIẾN THỨC QUAN TRỌNG KHI CONTAINERIZE VỚI DOCKER. ========================================
1. Thông thường, chúng ta có thể chỉ cần bỏ file jar vào trong Docker rồi chạy các commands để thực thi jar file đó. Nhưng điều này sẽ dẫn tới việc giảm performance. Bên cạnh đó, việc chạy jar file bên trong docker container sẽ gây khó khi fix các lỗi phát sinh trong quá trình chạy container. Vì code changes của chúng ta nằm trong jar và jar lại nằm trong container.
2. Vì 2 lý do trên, chúng ta sẽ extract jar file, rồi thực thi command để chạy ứng dụng trực tiếp giống như ở local.
   - Để extract được, chúng ta sẽ phải extract ở layertools(tools). Mà có một lưu ý quan trọng là layertools không thể sử dụng với _fully executable" (_https://docs.spring.io/spring-boot/reference/packaging/container-images/dockerfiles.html_). Vì _fully executable_ này bao gồm _launch script_. Do đó, để run ở chế độ layertools chúng ta phải cần disable _launch script_.
      - Mà do trong file pom.xml trước đó, chúng ta đã vô tình cài đặt _fully executable_ bằng cách:
        <plugin>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-maven-plugin</artifactId>
          <configuration>
          <executable>true</executable>
          </configuration>
        </plugin>
      - Nên điều này dẫn tới chúng ta đã cài đặt _fully executable_ rồi. Điều này dẫn tới lỗi trong một thời gian dài.
      - Vì vậy để có thể disable launch script thì chúng ta chỉ cần xoá đi cấu hình này trong _pom.xml_
3. Với việc disable launch script thì chúng ta đã có thể build image file thành công.

======================================== KIẾN THỨC QUAN TRỌNG CI/CD VỚI GITHUB ACTIONS - AZURE. ========================================
1. Để có thể thực hiện CI(continuous integration) với Github Actions thì đọc article này: _https://docs.github.com/en/actions/use-cases-and-examples/building-and-testing/building-and-testing-java-with-maven_
2. Để có thể thực hiện CD(continuous deployment) với Github Actions và Azure App Service thì đọc article này(Lưu ý: Phần này là deploy container, nó sẽ khác với Deploy Java App bằng jar file): _https://docs.github.com/en/actions/use-cases-and-examples/deploying/deploying-java-to-azure-app-service_