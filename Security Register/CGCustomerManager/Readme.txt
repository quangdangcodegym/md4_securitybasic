//----------------------------
dependencies: hibernate validation
Xác định các thuộc tính cần validate, để tạo lớp implements Validator
new ProvinceValidator().validate(province, bindingResult);


//----------------------------
Sử dụng hibernate
implementation group: 'org.hibernate', name: 'hibernate-core', version: '5.3.0.Final'
implementation group: 'org.hibernate', name: 'hibernate-entitymanager', version: '5.4.10.Final'
//----------------------------
- Sử dụng modelattribute để tạo ra biến toàn cục categories
@ModelAttribute
public void commonModelAttributes(Model model){
    model.addAttribute("categories", categoryService.findAllCategories());
}
- Nho them Anotation @ControllerAdvice
- Phan view chi viec lay va su dung
//----------------------------

Spring validation use hibernate
https://www.baeldung.com/spring-thymeleaf-error-messages
use custom messages resource
https://www.baeldung.com/spring-custom-validation-message-source



