//package com.myclass.validator;
//
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//import com.myclass.dto.SignUpDTO;
//
//@Component
//public class PasswordValidator implements Validator {
//
//	public boolean supports(Class<?> clazz) {
//		// TODO Auto-generated method stub
//		return SignUpDTO.class.equals(clazz);
//	}
//
//	public void validate(Object target, Errors errors) {
//		// Ép kiểu đối tượng truyền vào để validation
//		SignUpDTO signUpDTO = (SignUpDTO)target;
//		
//		// Kiểm tra xem password và confirm có giống nhau không
//		if(signUpDTO.getConfirm() == null 
//				|| !signUpDTO.getConfirm().equals(signUpDTO.getPassword())) {
//			// Nếu có lỗi thì gắn lỗi vào cho BindingResult
//			errors.rejectValue("confirm", "Mật khẩu không khớp!");
//		}
//	}
//
//}
