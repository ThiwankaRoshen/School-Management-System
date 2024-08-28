package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable{
	
	
	Connection con;
	PreparedStatement pst1;
	ResultSet rst;
	String url = "jdbc:mysql://localhost:3307/rewathaschool";
	String user = "root";
	String password = "";
	
	
	@FXML Button loginbtn;
	@FXML TextField usernameFeild;
	@FXML PasswordField userPasswordFeild;
	@FXML RadioButton studentRadioBtn,teacherRadioBtn,adminRadioBtn;
	
	
	String role = "";
	public static String username; 
	
	
	public LoginController() {
		connect();
	}
	
	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				con = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void handleLogin(Event mouseEvent) {
		if(mouseEvent.getSource()== loginbtn) {
			String userNameString = usernameFeild.getText();
			username = usernameFeild.getText();
			String passwordString = userPasswordFeild.getText();
			
			if(userNameString.isEmpty()) {
				Alert alert = new Alert(Alert.AlertType.ERROR) ;
				alert.setTitle("Error");
				alert.setContentText("Please fill out the username feild");
				alert.show();
			}else if(passwordString.isEmpty()) { 
				Alert alert2 = new Alert(Alert.AlertType.ERROR) ;
				alert2.setTitle("Error");
				alert2.setContentText("Please fill out the password feild");
				alert2.show();
			}else if(role.isEmpty()) {
				Alert alert3 = new Alert(Alert.AlertType.ERROR) ;
				alert3.setTitle("Error");
				alert3.setContentText("Please select Your role");
				alert3.show();
			}else {
				if(role.equals("student")) {
					try {
						pst1 = con.prepareStatement("SELECT * FROM student_usernamepassword WHERE index_number= ? AND password= ?" );
						pst1.setString(1, userNameString);
						pst1.setString(2, passwordString);
						rst = pst1.executeQuery();
						if(rst.next()) {
							Alert alert4 = new Alert(Alert.AlertType.INFORMATION) ;
//							alert4.setTitle("Information Message");
//							alert4.setContentText("Successfully login as Admin");
//							alert4.show();
							loginbtn.getScene().getWindow().hide();
							
							try {
								Parent root = FXMLLoader.load(getClass().getResource("StudentDashBoard.fxml"));
								Stage stage = new Stage();
								Scene scene = new Scene(root);
								stage.setScene(scene);
								stage.show();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else {
							Alert alert4 = new Alert(Alert.AlertType.ERROR) ;
							alert4.setTitle("Error");
							alert4.setContentText("Wrong username or password");
							alert4.show();
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else if (role.equals("teacher")) {
					try {
						pst1 = con.prepareStatement("SELECT * FROM teacher_usernamepassword WHERE registration_number= ? AND password= ?" );
						pst1.setString(1, userNameString);
						pst1.setString(2, passwordString);
						rst = pst1.executeQuery();
						if(rst.next()) {
							Alert alert4 = new Alert(Alert.AlertType.INFORMATION) ;
//							alert4.setTitle("Information Message");
//							alert4.setContentText("Successfully login as Teacher");
//							alert4.show();
							loginbtn.getScene().getWindow().hide();
							
							try {
								Parent root = FXMLLoader.load(getClass().getResource("TeacherDashBoard.fxml"));
								Stage stage = new Stage();
								Scene scene = new Scene(root);
								stage.setScene(scene);
								stage.show();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else {
							Alert alert4 = new Alert(Alert.AlertType.ERROR) ;
							alert4.setTitle("Error");
							alert4.setContentText("Wrong username or password");
							alert4.show();
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if (role.equals("admin")) {
					try {
						pst1 = con.prepareStatement("SELECT * FROM admin_usernamepassword WHERE adminName = ? AND password = ?" );
						pst1.setString(1, userNameString);
						pst1.setString(2, passwordString);
						rst = pst1.executeQuery();
						if(rst.next()) {
							Alert alert4 = new Alert(Alert.AlertType.INFORMATION) ;
//							alert4.setTitle("Information Message");
//							alert4.setContentText("Successfully login as Admin");
//							alert4.show();
							loginbtn.getScene().getWindow().hide();
							
							try {
								
								Parent root = FXMLLoader.load(getClass().getResource("DashBoard.fxml"));
								Stage stage = new Stage();
								Scene scene = new Scene(root);
								stage.setScene(scene);
								stage.show();
								stage.setTitle("Kuli/ Sri Rewatha Rathanapala College");
								
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}else {
							Alert alert4 = new Alert(Alert.AlertType.ERROR) ;
							alert4.setTitle("Error");
							alert4.setContentText("Wrong username or password");
							alert4.show();
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void handleRadioButton(ActionEvent event) {
		if(studentRadioBtn.isSelected()) {
			role = "student";
		}else if(teacherRadioBtn.isSelected()) {
			role = "teacher";
		}else if(adminRadioBtn.isSelected()) {
			role = "admin";
		}else {
			role ="";
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
