package application;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class TeacherDashBoardController implements Initializable {

	Connection con;
	PreparedStatement pst, pst2;
	ResultSet rst, rst1, rst2;
	String url = "jdbc:mysql://localhost:3307/rewathaschool";
	String username = "root";
	String password = "";

	@FXML
	Label s71, s81, s91, s101, s111, s121;
	
	@FXML
	TextField examSubject13,examSubject21,examSubject31,examSubject41,examSubject51,examSubject61,examSubject71, examSubject81,examSubject91,examSubject101,examSubject111,examSubject121;
	@FXML
	ChoiceBox<String> subjTeach1,subjTeach2,subjTeach3,subjTeach4,subjTeach5,subjTeach6,subjTeach7,subjTeach8	,subjTeach9	,subjTeach10,subjTeach11,subjTeach12;
	
	@FXML
	Button	upload1	,upload2,upload3;
	
	
	@FXML
	Label s72, s82, s92, s102, s112, s122;
	
	@FXML
	TextField examSubject14,examSubject22,examSubject32,examSubject42,examSubject52,examSubject62,examSubject72, examSubject82,examSubject92,examSubject102,examSubject112,examSubject122;
	
	
	@FXML
	Button	u1	,u2,u3,u4	,u5,u6,u7	,u8,u9,u10	,u11,u12;
	@FXML
	ChoiceBox<String> classYear;
	@FXML
	ChoiceBox<String>classGrade;
	@FXML
	ChoiceBox<String>classClass;
	@FXML
	ChoiceBox<String>classSub;
	
	@FXML
	BarChart <?,?>barChart;
	@FXML
	BarChart <?,?>barChart2;
	@FXML
	PieChart pieChart;
	
	// Declare all buttons
	@FXML
	Button registerForm;
	@FXML
	Button searchAndEdit;
	@FXML
	Button createClass;
	@FXML
	Button createSubject;
	@FXML
	Button scheduleExam;
	@FXML
	Button uploadResult;
	@FXML
	Button viewPerfomance;
	@FXML
	Button addNotice;
	@FXML
	Button viewNotices;
	@FXML
	Button classStudExcel;
	@FXML
	Button addPayments;
	@FXML
	Button viewPayments;
	@FXML
	Button addAchievements;
	@FXML
	Button leavingForm;
	@FXML
	Button registerStudent;
	@FXML
	Button registerTeacher;
	@FXML
	Button searchStudentB;
	@FXML
	Button searchStudent2B;
	@FXML
	Button searchStudent3B;
	@FXML
	Button searchTeacherB;
	@FXML
	Button searchTeacher2B;
	@FXML
	Button searchTeacher3B;
	@FXML
	Button editStudentB;
	@FXML
	Button editTeacherB;
	@FXML
	Button editStudent2B;
	@FXML
	Button editStudent3B;
	@FXML
	Button editTeacher2B;
	@FXML
	Button editTeacher3B;
	@FXML
	Button viewStudentPerformanceB;
	@FXML
	Button viewTeacherPerformancesB;
	@FXML
	Button viewClassPerformancesB;
	@FXML
	Button viewStudentPerformance2B;
	@FXML
	Button viewTeacherPerformances2B;
	@FXML
	Button viewClassPerformances2B;
	@FXML
	Button addAchievementsB;
	@FXML
	Button addSportsB;
	@FXML
	Button notices;
	@FXML
	Button payments;
	@FXML
	Button mainDashboard;
	@FXML
	Button logoutBtn;
	// Declare all anchors
	@FXML
	AnchorPane registerStudents;
	@FXML
	AnchorPane registerTeachers;
	@FXML
	AnchorPane searchStudent;
	@FXML
	AnchorPane searchTeacher;
	@FXML
	AnchorPane editStudent;
	@FXML
	AnchorPane editTeacher;
	@FXML
	AnchorPane createClassA;
	@FXML
	AnchorPane createSubjectA;
	@FXML
	AnchorPane scheduleExamA;
	@FXML
	AnchorPane uploadResultA;
	@FXML
	AnchorPane viewStudentPerformance;
	@FXML
	AnchorPane viewTeacherPerformance;
	@FXML
	AnchorPane viewClassPerformance;
	@FXML
	AnchorPane addNoticesA;
	@FXML
	AnchorPane viewNoticesA;
	@FXML
	AnchorPane addPaymentsA;
	@FXML
	AnchorPane viewPaymentsA;
	@FXML
	AnchorPane addAchievementsA;
	@FXML
	AnchorPane addSport;
	@FXML
	AnchorPane leavingFormA;
	@FXML
	AnchorPane dashBoard;
// Declare main dashboard label 
	@FXML
	Label studentCountlbl;
	@FXML
	Label teacherCountlbl;
// Declare all controls for register Students pane
	// Declare all textfields
	@FXML
	TextField resultsMarks;
	@FXML
	TextField indexplot;
	@FXML
	TextField stdindexNumber;
	@FXML
	TextField stdfullname;
	@FXML
	TextField stdcontactDetails;
	@FXML
	TextField stdguardianName;
	@FXML
	TextField stdbeforeSchoolName;
	@FXML
	TextField stdRegistrationGrade;
	@FXML
	TextField stdRegistrationClass;
	// Declare all radioButtons
	@FXML
	RadioButton stdmaleRadioBtn;
	@FXML
	RadioButton stdfemaleRadioBtn;
	// Declare all date Pickers
	@FXML
	DatePicker stddateOfBirth;
	@FXML
	DatePicker stdRegistrationDate;
	// Declare all choice boxes
	@FXML
	ChoiceBox<String> stdNationality;
	@FXML
	ChoiceBox<String> uplosubject;
	@FXML
	ChoiceBox<String> stdReligion;
	String[] religion = { "Buddist", "Catholic", "Islam" };
	// Declare textarea
	@FXML
	TextArea guardianAddress;
	// Declare register button
	@FXML
	Button stdRegister;
//Declare handle event method 
//Declare all controls for register teacher 
	@FXML
	TextField techRegNumber;
	@FXML
	TextField techFullName;
	@FXML
	TextField techNICNumber;
	@FXML
	TextField techContactNumber;
	@FXML
	TextField techCurrentGrade;
	@FXML
	TextField techCurrentSubject;
	// Declare all radioButtons
	@FXML
	RadioButton techMarried;
	@FXML
	RadioButton techSingle;
	@FXML
	RadioButton techMale;
	@FXML
	RadioButton techFemale;
	@FXML
	RadioButton techTrainedDegree;
	@FXML
	RadioButton techDiploma;
	// Declare all date Pickers
	@FXML
	DatePicker techDateOfBirth;
	@FXML
	DatePicker techDateForTheDivision;
	@FXML
	DatePicker techDateForTheSchool;
	// Declare all choice boxes

	// Declare textarea
	@FXML
	TextArea techPrivateAddress;
	// Declare register button
	@FXML
	Button techRegisterBtn;

	@FXML
	Label classPlace;
	@FXML
	Label s9;
	@FXML
	Label s8;
	@FXML
	Label s7;
	@FXML
	Label s10;
	@FXML
	Label s11;
	@FXML
	Label s12;
	
	@FXML
	Button searchstudentSearchBtn;
	@FXML
	TextField searchstudentSearchTextField;
	@FXML
	Label searchStudentIndexNumber;
	@FXML
	Label searchStudentFullName;
	@FXML
	Label searchStudentGender;
	@FXML
	Label searchStudentContactNumber;
	@FXML
	Label searchStudentDateOfBirth;
	@FXML
	Label searchStudentRegistrationDate;
	@FXML
	Label searchStudentNationality;
	@FXML
	Label searchStudentReligion;
	@FXML
	Label searchStudentGuardianName;
	@FXML
	Label searchStudentGuardianAddress;
	@FXML
	Label searchStudentRegisteredGrade;
	@FXML
	Label searchStudentRegisteredClass;
	@FXML
	Label searchStudentSchoolAttendedBefore;
	// search Teacherpart
	@FXML
	Button searchTeacherSearchBtn;
	@FXML
	TextField searchTeacherSearchTextField;
	@FXML
	Label searchTeacherRegistrationNumber;
	@FXML
	Label searchTeacherFullName;
	@FXML
	Label searchTeacherCivilStatus;
	@FXML
	Label searchTeacherGender;
	@FXML
	Label searchTeacherDateOfBirth;
	@FXML
	Label searchTeacherNICNumber;
	@FXML
	Label searchTeacherPrivateAddress;
	@FXML
	Label searchTeacherContactNumber;
	@FXML
	Label searchTeacherCurrentGrade;
	@FXML
	Label searchTeacherCurrentSubject;
	@FXML
	Label searchTeacherTeacherType;
	@FXML
	Label searchTeacherDivisionDate;
	@FXML
	Label searchTeacherSchoolDate;
	// edit Student
	@FXML
	TextField editStdIndexNumber;
	@FXML
	TextField editStdFullName;
	@FXML
	TextField editStdContactDetails;
	@FXML
	TextField editStdGuardianName;
	@FXML
	TextField editStdBeforeSchoolName;
	@FXML
	TextField editStdRegistrationGrade;
	@FXML
	TextField editStdRegistrationClass;

	// Radio buttons
	@FXML
	RadioButton editStdMaleRadioBtn;
	@FXML
	RadioButton editStdFemaleRadioBtn;

	// Date pickers
	@FXML
	DatePicker editStdDateOfBirth;
	@FXML
	DatePicker editStdRegistrationDate;

	// Choice boxes
	@FXML
	ChoiceBox<String> editStdNationality;
	@FXML
	ChoiceBox<String> editStdReligion;
	
	
	// Choice boxes
	@FXML
	ChoiceBox<String> selectGrade;
	@FXML
	ChoiceBox<String> selectSubject;
	@FXML
	ChoiceBox<String> selectGrade1;
	@FXML
	ChoiceBox<String> selectSubject1;
	
	@FXML
	ChoiceBox<String>teachName;
	@FXML
	ChoiceBox<String>stdName;
	
	@FXML
	ChoiceBox<String>termplot;
	@FXML
	ChoiceBox<String>yearplot;
	@FXML
	ChoiceBox<String>examyear;
//	@FXML
//	ChoiceBox<String>subjstud;
	@FXML
	ChoiceBox<String>subjTeach;
	
	
	// Text area
	@FXML
	TextArea editGuardianAddress;

	// Button
	@FXML
	Button editStdSearchButton;
	@FXML
	Button createClassteacherInsertBtn1;
	@FXML
	Label ClassTeacher;
	@FXML
	Button editStudentApplyBtn;
	@FXML
	Button editStudentClearAllBtn;
	@FXML
	Button editStudentCancelBtn;

	@FXML
	TextField editStudentIndexTextField;
	// edit Teacher
	// Textfeild
	@FXML
	TextField editTeacherRegistrationTextField;
	@FXML
	TextField editTeacherRegistrationNumber;
	@FXML
	TextField editTeacherFullName;
	@FXML
	TextField editTeacherNIC;
	@FXML
	TextField editTeacherContactNumber;
	@FXML
	TextField editTeacherCurrentGrade;
	@FXML
	TextField editTeacherCurrentSubject;
	// Buttons
	@FXML
	Button editTeacherSearchButton;
	@FXML
	Button editTeacherApplyBtn;
	@FXML
	Button editTeacherClearAllBtn;
	@FXML
	Button editTeacherCancelBtn;
	// RadioButtons
	@FXML
	RadioButton teachSecondary;
	@FXML
	RadioButton teachPrimary;
	
	@FXML
	RadioButton editTeacherMaleRadioBtn;
	@FXML
	RadioButton editTeacherFemaleRadioBtn;
	@FXML
	RadioButton editTeacherMarriedRadioBtn;
	@FXML
	RadioButton editTeacherSingleRadioBtn;
	@FXML
	RadioButton editTeacherTrainedRadioBtn;
	@FXML
	RadioButton editTeacherDiplomaRadioBtn;
	// DatePicker
	@FXML
	DatePicker editTeacherDateOfBirth;
	@FXML
	DatePicker editTeacherDivisionDate;
	@FXML
	DatePicker editTeacherSchoolDate;
	// textArea
	@FXML
	TextArea editTeacherTextArea;
	
	@FXML
	TableView<PersonRecord> tempStudentEnroll;
	@FXML
	TableColumn<PersonRecord, String> colNo;
	@FXML
	TableColumn<PersonRecord, String> colIndex_Number;
	@FXML
	TableColumn<PersonRecord, String> colName;

	ObservableList<PersonRecord> studentList = FXCollections.observableArrayList();
	
	@FXML
	TableView<StudentSubject> tempStudentSubjecEnroll;
	@FXML
	TableColumn<StudentSubject, String> colsNo;
	@FXML
	TableColumn<StudentSubject, String> colsIndex_Number;
	@FXML
	TableColumn<StudentSubject, String> colsName;

	ObservableList<StudentSubject> studentSubjectList = FXCollections.observableArrayList();
	@FXML
	TextField regsportsName;
	@FXML
	TextField regcoachName;

	

	@FXML
	Button registerSportApplyButton;
	@FXML
	Button addAchievementsApplyButton;

	ArrayList<String> sportList = new ArrayList<>();

	@FXML
	TextField achievementsIndexNo;
	@FXML
	TextField achievementsEventName;
	@FXML
	TextField achievementAchievement;
	@FXML
	DatePicker achievementsDate;
	@FXML
	ChoiceBox<String> acheivementType;
	
	@FXML
	Button leavingFormSearchBtn;
	@FXML
	Button leavingFormPrintBtn;
	@FXML
	TextField leavingFormSearchTextField;
	@FXML
	Label leavingFormIndexNumber;
	@FXML
	Label leavingFormFullName;
	@FXML
	Label leavingFormGender;
	@FXML
	Label leavingFormContactNumber;
	@FXML
	Label leavingFormDateOfBirth;
	@FXML
	Label leavingFormRegistrationDate;
	@FXML
	Label leavingFormNationality;
	@FXML
	Label leavingFormReligion;
	@FXML
	Label leavingFormGuardianName;
	@FXML
	Label leavingFormGuardianAddress;
	@FXML
	Label leavingFormRegisteredGrade;
	@FXML
	Label leavingFormRegisteredClass;
	@FXML
	Label leavingFormSchoolAttendedBefore;
	@FXML
	Label leavingFormAcheivement1;
	@FXML
	Label leavingFormAcheivement2;
	@FXML
	Label leavingFormAcheivement3;
	@FXML
	Label leavingFormAcheivement4;
	@FXML
	Label leavingFormAcheivement1Details;
	@FXML
	Label leavingFormAcheivement2Details;
	@FXML
	Label leavingFormAcheivement3Details;
	@FXML
	Label leavingFormAcheivement4Details;
	@FXML
	Label leavingFormSchoolLeavingDate;
	@FXML
	Label leavingFormSchoolTimePeriod;

	@FXML
	TextField paymentDetailsIndexNumber;
	@FXML
	TextField paymentDetailsInvoiceNumber;
	@FXML
	TextField paymentDetailsPaymentName;
	@FXML
	DatePicker paymentDetailsPaymentDate;
//	@FXML
//	ChoiceBox<String> paymentDetailsGradeChoice;
	@FXML
	TextField paymentDetailsAmout;
	
	
	@FXML
	TextField perfteachid;
	@FXML
	ChoiceBox<String> perfClass;
	@FXML
	ChoiceBox<String> perfGrade;
	@FXML
	ChoiceBox<String> perfYear;
	
	@FXML
	TextArea paymentDetailsPaymentSpecialNote;
	@FXML
	Button paymentDetailsPayBtn;
	@FXML
	Button paymentDetailsSearchBtn;
	@FXML
	Button paymentDetailsCancelBtn;

	@FXML
	TableView<Payment> tempPaymentDetails;
	@FXML
	TableColumn<Payment, Integer> colpNo;
	@FXML
	TableColumn<Payment, String> colpInvoice_Number;
	@FXML
	TableColumn<Payment, String> colpPayment_Number;
	@FXML
	TableColumn<Payment, String> colpAmout;

	// fxlm for exam schedule
	@FXML
	ChoiceBox<String> examName1;
	@FXML
	ChoiceBox<String> examGrade;
	@FXML
	RadioButton examMonthlyRadio, examTermlyRadio;
	@FXML
	RadioButton randButton, uploadButton, manualButton;
	@FXML
	TextField examSubject1, examSubject2, examSubject3, examSubject4, examSubject5, examSubject6, examSubject7,
			examSubject8, examSubject9, examSubject10, examSubject11, examSubject12;
	@FXML
	DatePicker examDate1, examDate2, examDate3, examDate4, examDate5, examDate6, examDate7, examDate8, examDate9,
			examDate10, examDate11, examDate12;
	@FXML
	TextField examTime1, examTime2, examTime3, examTime4, examTime5, examTime6, examTime7, examTime8, examTime9,
			examTime10, examTime11, examTime12;
	@FXML
	TextArea examAdditionalDetails;
	@FXML
	Button examAddButton;

	// declare all controls for upload result
	@FXML
	ChoiceBox<String> resultSubject;
	@FXML
	ChoiceBox<String> resultGrade;
//	@FXML
//	ChoiceBox<String> resultClass;
	@FXML
	ChoiceBox<String> resultYear;
	@FXML
	ChoiceBox<String> resultTerm;
	@FXML
	TextField resultIndexNumber;
	@FXML
	TextField resultMarks;
	@FXML
	Button resultAddBtn;
	@FXML
	Button resultSubmitBtn;
	@FXML
	TableView<ResultRecord> resultTempDetails;
	@FXML
	TableColumn<ResultRecord, String> idxcol;
	@FXML
	TableColumn<ResultRecord, String> marks;
	ObservableList<ResultRecord> resultList = FXCollections.observableArrayList();

	// declare all controls for notice
	@FXML
	TextArea noticeTextarea;
	@FXML
	Button noticeAddBtn;
	@FXML
	Label noticeEmptyText;
	@FXML
	VBox noticeVBox;

	// declare all controls for view payment
	@FXML
	TextField viewPaymentIndex;
	@FXML
	TableView<PaymentRecord> viewPaymentTable;
	@FXML
	TableColumn<PaymentRecord, Integer> viewPaymentInvoice;
	@FXML
	TableColumn<PaymentRecord, String> viewPaymentName;
	@FXML
	TableColumn<PaymentRecord, String> viewPaymentPaymentDate;
	@FXML
	TableColumn<PaymentRecord, Integer> viewPaymentPaymentAmount;
	@FXML
	Button viewPaymentSearchBtn;
	ObservableList<PaymentRecord> viewPaymentList = FXCollections.observableArrayList();
	
	@FXML
	Label teachSubjLabel;
	
	@FXML
	Label gradeLabel1;
	@FXML
	Label teachSubjLabel1;
	@FXML
    private Label greeting;
	@FXML
	RadioButton teachPrimary1;
	@FXML
	RadioButton teachSecondary1;
	
	@FXML
	RadioButton editTeacherTrainedRadioBtn1;
	@FXML
	RadioButton editTeacherTrainedRadioBtn2;
	
	@FXML
	RadioButton editTeacherDiplomaRadioBtn1;
	@FXML
    private Button changePassword;


    @FXML
    private Label currpass;
    @FXML
    private Label newpass;

    @FXML
    private Label fillcurrpass;

    @FXML
    private TextField fillnewpass;
    

    @FXML
    private Button updatebtn;
    
    @FXML
    private ImageView schlimg;
	
    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    
    
	@FXML
	TableView<NoticeRecord> noticeTable;
	@FXML
	TableColumn<NoticeRecord, String> datecol;
	@FXML
	TableColumn<NoticeRecord, String> noticecol;

	ObservableList<NoticeRecord> viewNoticeList = FXCollections.observableArrayList();
	

	String username1;
	Date date = new Date(0);
	int startYear = 2000;
    Calendar calendar = Calendar.getInstance();
    int endYear = calendar.get(Calendar.YEAR); // Current year

    // Create an ObservableList and populate it with the range of years
    ObservableList<String> years = FXCollections.observableArrayList();
    
    String[] terms = {
    	    "First", "Second", "Third", 
    	    "January", "February", "March", "April", "May", "June", 
    	    "July", "August", "September", "October", "November", "December"
    	};
    
    String[] termL = {
    	    "First", "Second", "Third"
    	};
    String[] monthL = {
    	    "January", "February", "March", "April", "May", "June", 
    	    "July", "August", "September", "October", "November", "December"
    	};

    
	String[] grade = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11" };
	
	String[] clases = { "A", "B", "C", "D", "E", "F" };
	
	String[] grade1_5Subjects = { "Sinhala", "Mathematics", "Religion", "Tamil", "Environment", "English" };
	
	String[] grade6_7Subjects = { "Sinhala", "Mathematics", "Science", "Religion", "English", "History", "Tamil",
			"P.T.S.", "Civics", "Geography", "Health", "Aesthetic" };
	String[] grade10_11Subjects = { "Sinhala", "Mathematics", "Religion", "History", "Science", "English",
			"Optional Subject1", "Optional Subject2", "Optional Subject3" };

	public TeacherDashBoardController() {
		connect();
	}

	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				con = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// implement database update method
	private int executeUpdate(String sql, Object... params) throws SQLException {
		try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
			for (int i = 0; i < params.length; i++) {
				preparedStatement.setObject(i + 1, params[i]);
			}
			return preparedStatement.executeUpdate();
		}
	}

	// implement database get method
	private ResultSet executeQuery(String sql, Object... params) throws SQLException {
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			preparedStatement.setObject(i + 1, params[i]);
		}
		return preparedStatement.executeQuery();
	}

	// implement show alertMethod
	private void showAlert(Alert.AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setContentText(message);
		alert.show();
	}

	// method to handle all events in mainDashboard
	public void handleMainDashboard(Event mouseEvent) throws SQLException, IOException {
		
		uploadResultA.setVisible(false);
		viewStudentPerformance.setVisible(false);
		viewTeacherPerformance.setVisible(false);
		viewClassPerformance.setVisible(false);
		addNoticesA.setVisible(false);
		viewNoticesA.setVisible(false);
		dashBoard.setVisible(true);
		
		
		schlimg.setVisible(true);
		greeting.setVisible(true);
		
		pane1.setVisible(true);
		pane2.setVisible(true);
		
		currpass.setVisible(false);
		fillcurrpass.setVisible(false);
		newpass.setVisible(false);
		fillnewpass.setVisible(false);
		updatebtn.setVisible(false);
		
		String sql1 = "SELECT * FROM teacher_details where registration_number = ?";
		try {
			rst2 = executeQuery(sql1, username1);
			String Uname = null;
			if(rst2.next()) {
				Uname = rst2.getString("full_name");
			}
			greeting.setText("Welcome to The LMS, "+ Uname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String sql = "SELECT COUNT(index_number) AS student_count FROM students_details";
		rst = executeQuery(sql);

		if (rst.next()) {
			int studentCount = rst.getInt("student_count");
			studentCountlbl.setText(Integer.toString(studentCount));
		} else {
			studentCountlbl.setText("0");
		}

		String sql2 = "SELECT COUNT(registration_number) AS teacher_count FROM teacher_details";
		rst = executeQuery(sql2);

		if (rst.next()) {
			int teacherCount = rst.getInt("teacher_count");
			teacherCountlbl.setText(Integer.toString(teacherCount));
		} else {
			teacherCountlbl.setText("0");
		}

	}

	
	// method to handle all invents in result
	public void handleResult(Event mouseEvent) {
		if (mouseEvent.getSource() == uploadResult) {
			
			uploadResultA.setVisible(true);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(false);
			dashBoard.setVisible(false);

		}
	}

	// method to handle all invents in performance
	public void handlePerformance(Event mouseEvent) {
		if (mouseEvent.getSource() == viewPerfomance || mouseEvent.getSource() == viewStudentPerformanceB
				|| mouseEvent.getSource() == viewStudentPerformance2B) {
			
			
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(true);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(false);
			dashBoard.setVisible(false);

		} else if (mouseEvent.getSource() == viewTeacherPerformancesB
				|| mouseEvent.getSource() == viewTeacherPerformances2B) {
			
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(true);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(false);
			dashBoard.setVisible(false);

		} else if (mouseEvent.getSource() == viewClassPerformancesB
				|| mouseEvent.getSource() == viewClassPerformances2B) {
			
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(true);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(false);
			dashBoard.setVisible(false);

		}
	}

	// method to handle all invents in notices
	public void handleNotices(Event mouseEvent) {
		if (mouseEvent.getSource() == addNotice || mouseEvent.getSource() == notices) {
			
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(true);
			viewNoticesA.setVisible(false);
			dashBoard.setVisible(false);

		} else if (mouseEvent.getSource() == viewNotices) {
			
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(true);
			dashBoard.setVisible(false);
			
			viewNoticeList.clear();
			// get all notices from the database
			try {
				String sql = "SELECT * FROM notice_details";
				rst = executeQuery(sql);

				
			
				// add notices as label to the anchor pane
				while (rst.next()) {
					sql = "SELECT * FROM notice_details";
					rst = executeQuery(sql);
					
					// if there are payments
					while(rst.next()){
						viewNoticeList.add(new NoticeRecord(rst.getString("notice"), rst.getString("notice_date")));
						noticeTable.setItems(viewNoticeList);
					}
					// set table columns
					noticecol.setCellValueFactory(new PropertyValueFactory<NoticeRecord, String>("name"));
					datecol.setCellValueFactory(new PropertyValueFactory<NoticeRecord, String>("date"));

					

				}
			
			} catch (SQLException e) {
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to get notices: " + e.getMessage());
			}
			
			try {
				String sql = "SELECT * FROM exam_details";
				rst = executeQuery(sql);

				
			
				// add notices as label to the anchor pane
				while (rst.next()) {
					sql = "SELECT * FROM exam_details";
					rst = executeQuery(sql);
					
					while(rst.next()){
						int count = 0;
						String ExmId = rst.getString("exam_id");
//						viewNoticeList.add(new NoticeRecord("(Exam ID)"+rst.getString("exam_id")+": "+rst.getString("name")+" "+rst.getString("type")+" Exam for Grade "+rst.getString("grade"), "Down Below !"));
//						noticeTable.setItems(viewNoticeList);
						String sql1 = "SELECT * FROM exam_subjectdetails where exam_id = ?";
						rst2 = executeQuery(sql1, Integer.valueOf(ExmId));
						
						while(rst2.next()) {
							if(Date.valueOf(rst2.getString("exam_date")).after((Date.valueOf(LocalDate.now())))){
								
								if(count == 0) {
									viewNoticeList.add(new NoticeRecord("(Exam ID)"+rst.getString("exam_id")+": "+rst.getString("name")+" "+rst.getString("type")+" Exam for Grade "+rst.getString("grade"), "Down Below !"));
									noticeTable.setItems(viewNoticeList);
								}
								count++;
								viewNoticeList.add(new NoticeRecord("(Exam ID)"+rst.getString("exam_id")+": "+"Subject: "+rst2.getString("subject_name"), rst2.getString("exam_date")+" : "+rst2.getString("time")));
								noticeTable.setItems(viewNoticeList);
							}
							
						}
						
						
					}
					// set table columns
					noticecol.setCellValueFactory(new PropertyValueFactory<NoticeRecord, String>("name"));
					datecol.setCellValueFactory(new PropertyValueFactory<NoticeRecord, String>("date"));

					

				}
			
			} catch (SQLException e) {
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to get notices: " + e.getMessage());
			}

		}else if(mouseEvent.getSource() == noticeAddBtn){
			String notice = noticeTextarea.getText();
			if(notice.isEmpty()){
				showAlert(Alert.AlertType.ERROR, "Error", "Please enter a notice");
			}else{
				try {
					String sql = "INSERT INTO notice_details(notice, notice_date) VALUES(?,?)";
					int k = executeUpdate(sql, notice,LocalDate.now());
					if(k > 0){
						showAlert(Alert.AlertType.INFORMATION, "Success", "Notice added successfully");
						noticeTextarea.clear();
					}else{
						showAlert(Alert.AlertType.ERROR, "Error", "Failed to add notice");
					}
				} catch (SQLException e) {
					showAlert(Alert.AlertType.ERROR, "Error", "Failed to add notice: " + e.getMessage());
				}
			}
		}
	}

	
	// method to handle logout
	public void handleLogout(Event mouseEvent) throws IOException {
		logoutBtn.getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		stage.setTitle("Kuli/ Sri Rewatha Rathanapala College");
	}
	

	
	
	
	@FXML
	public void  uplo1(ActionEvent event) throws SQLException {
		
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Open File Dialog");

		Stage stage = (Stage)uploadResultA.getScene().getWindow();
		
		File file = filechooser.showOpenDialog(stage);
		String grade = resultGrade.getValue();
		
		String subjectID = null;
		String examID = null;
		
		String subjectName = uplosubject.getValue();
		
		String sql2 = "select subject_id from subject_details where grade = ? and subject_name = ? ";
		rst1 = executeQuery(sql2,  resultGrade.getValue(),  subjectName );
		if(rst1.next()) {
			subjectID = rst1.getString("subject_id");
		}
		
		sql2 = "select exam_id from exam_details where grade = ? and year = ?  and name = ?";
		rst1 = executeQuery(sql2,  resultGrade.getValue(),  resultYear.getValue(), resultTerm.getValue());
		if(rst1.next()) {
			examID = rst1.getString("exam_id");
		}
		
		
		
		
		try (FileInputStream fis = new FileInputStream(file);
		         XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
		        
		        XSSFSheet sheet =  workbook.getSheetAt(0);
		        int numOfColumns = sheet.getRow(0).getLastCellNum();

	           
	            	for (Row row : sheet) {
	            		
	                    int id = (int)row.getCell(0).getNumericCellValue();
	                    int mark = (int)row.getCell(1).getNumericCellValue();
	                    
	                    String sql = "INSERT INTO result_details(subject_id	,exam_id ,marks ,index_number) values(?,?,?,?)";
            			int k = executeUpdate(sql, subjectID, examID, mark , id);
            			
	                }
	                
	            
		        
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		 	catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
	
	
	@FXML
	public void removeNotice(ActionEvent event) throws SQLException {

		int selectedId = noticeTable.getSelectionModel().getSelectedIndex();
	    
	    // Check if a row is selected
	    if (selectedId >= 0) {
	        // Print the invoice of the selected item
	        System.out.println(noticeTable.getSelectionModel().getSelectedItems().get(0).getName()+"  , "+noticeTable.getSelectionModel().getSelectedItems().get(0).getDate());
	        String notName = noticeTable.getSelectionModel().getSelectedItems().get(0).getName();
	        String notDate = noticeTable.getSelectionModel().getSelectedItems().get(0).getDate();
	        
	        // Remove the selected item from the table
	        noticeTable.getItems().remove(selectedId);
	        deleteNoticeFromDatabase(notName, notDate);
	    } else {
	        // Handle the case where no row is selected
	        System.out.println("No row selected.");
	    }
	    
		
	}
	
	
	private void deleteNoticeFromDatabase(String name, String date) throws SQLException {
	     
        String sql1 = "DELETE FROM `notice_details` WHERE notice = ? and notice_date = ?";
        String sql2 = "DELETE FROM `exam_subjectdetails` WHERE exam_id = ?";
    
        String sql3 = "DELETE FROM `exam_details` WHERE exam_id = ?";

        String myStr = name;
        try{
        	if(myStr.substring(0, 9).equals("(Exam ID)")){
            	String temp = "";
                int i = 9;
                while(myStr.charAt(i) != ':'){
                	temp+=myStr.charAt(i);
                    i++;
                    
                }
                String examID = temp;
                System.out.println("EID : "+examID);
                
                PreparedStatement pstmt = con.prepareStatement(sql2);

                pstmt.setString(1, examID);
                int affectedRows = pstmt.executeUpdate();
                
                if (affectedRows > 0) {
                    System.out.println("notice deleted successfully.1");
                } else {
                    System.out.println("No notice found with the given notice details.1");
                }
                
                pstmt = con.prepareStatement(sql3);

                pstmt.setString(1, examID);
                affectedRows = pstmt.executeUpdate();
                
                if (affectedRows > 0) {
                    System.out.println("notice deleted successfully.2");
                } else {
                    System.out.println("No notice found with the given notice details.2");
                }
         
         
            }else {
            	PreparedStatement pstmt = con.prepareStatement(sql1);

                pstmt.setString(1, name);
                pstmt.setString(2, date);
                int affectedRows = pstmt.executeUpdate();
                
                if (affectedRows > 0) {
                    System.out.println("notice deleted successfully.");
                } else {
                    System.out.println("No notice found with the given notice details.");
                }
         
            }
        }
        catch(StringIndexOutOfBoundsException e) {
        	
        	System.out.println("caught");
        	PreparedStatement pstmt = con.prepareStatement(sql1);

            pstmt.setString(1, name);
            pstmt.setString(2, date);
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("notice deleted successfully.");
            } else {
                System.out.println("No notice found with the given notice details.");
            }
        }
        
             
    }
	
	
	
	@FXML
	public void printReport(ActionEvent event) throws SQLException{
		String id = indexplot.getText();
		String term = termplot.getValue();
		String year = yearplot.getValue();
//		String grade = null;
		String clas = null;
		
		String classID = null;
		String grade = null;
		String sql = "select classID, grade from class_details where classID in (select classID from class_studentdetails where index_number = ?) and year = ?";
		rst = executeQuery(sql, id, year);
		if(rst.next()) {
			classID = rst.getString("classID");
			grade = rst.getString("grade");
		}
		
		ObservableList<String> subj = FXCollections.observableArrayList();
		ObservableList<String> marks = FXCollections.observableArrayList();
		sql = "SELECT * FROM `result_details` where index_number  = ? and exam_id = (select exam_id from exam_details where grade = ? and  year = ? and name = ?)";
		rst = executeQuery(sql, id, grade, year, term);
		
		
//		sql = "SELECT * FROM `result_details` where student_id = ? and  year = ? and term = ?";
//		rst = executeQuery(sql, id, year, term);
		while(rst.next()) {
//			clas = rst.getString("class");
//			grade = rst.getString("grade");
			String sql1 = "SELECT subject_name FROM `subject_details` where subject_id = ?";
			rst1 = executeQuery(sql1, rst.getString("subject_id"));
			String subjName = null;
			if(rst1.next()) {
				subjName = rst1.getString("subject_name");
			}
			subj.add(subjName);
			marks.add(rst.getString("marks"));
		}  
		
		String name = null;
		sql = "SELECT full_name FROM `students_details` where index_number = ?";
		rst = executeQuery(sql, id);
		if(rst.next()) {
			name = rst.getString("full_name");
		}
		
		
		String avgMark = null;
		sql = "SELECT AVG(marks) as avg FROM `result_details` where index_number  = ? and exam_id = (select exam_id from exam_details where grade = ? and  year = ? and name = ?)";

//		sql = "SELECT AVG(marks) as avg FROM `result_details` where student_id = ? and  year = ? and term = ?";
		rst = executeQuery(sql, id, grade, year, term);
		if(rst.next()) {
			avgMark = rst.getString("avg");
		}
		
		String total = null;
		sql = "SELECT  SUM(marks) as tot FROM `result_details` where index_number  = ? and exam_id = (select exam_id from exam_details where grade = ? and  year = ? and name = ?)";

//		sql = "SELECT AVG(marks) as avg FROM `result_details` where student_id = ? and  year = ? and term = ?";
		rst = executeQuery(sql, id, grade, year, term);
//		sql = "SELECT SUM(marks) as tot FROM `result_details` where student_id = ? and  year = ? and term = ?";
//		rst = executeQuery(sql, id, year, term);
		if(rst.next()) {
			total = rst.getString("tot");
		}
		
		String position=null;
		sql = " select rank from (SELECT index_number, sum(marks), RANK() OVER (ORDER BY marks DESC) AS rank from result_details where index_number in ( select index_number from class_studentdetails where classID =(select classID from class_details where classID in (select classID from class_studentdetails where index_number = ?) and year = ?))  and  exam_id = (SELECT exam_id from exam_details where year =? and grade =? and name =? ) GROUP BY index_number ORDER by SUM(marks) desc) as results where index_number = ?;";
		rst = executeQuery(sql, id, year,year, grade, term, id);
		if(rst.next()) {
			position = rst.getString("rank");
		}

		
		
		String index = id;
		String dest = "ReportCardOf"+index+".pdf";
        Document document = new Document();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));

            // Define a rectangle for the border
            Rectangle pageSize = new Rectangle(PageSize.A4);
            pageSize.setBorder(Rectangle.BOX);
            pageSize.setBorderWidth(5);
            pageSize.setBorderColor(BaseColor.BLACK);

            // Set the rectangle as the page size
            document.setPageSize(pageSize);
            document.open();

            // Add header
            Font headerFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 16, BaseColor.BLACK);
            Paragraph header = new Paragraph("Kuli/ Sri Rewatha Rathanapala College\nStudent Report Card", headerFont);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);

            // Add some space after the header
            document.add(Chunk.NEWLINE);

            // Add logo
            Image logo = Image.getInstance("logo.png");
            logo.setAlignment(Element.ALIGN_CENTER);
            logo.scaleToFit(100, 100); // Adjust the size of the logo
            document.add(logo);

            // Add some space after the logo
            document.add(Chunk.NEWLINE);

            Font font = FontFactory.getFont(FontFactory.TIMES, 18, BaseColor.BLACK);
         // Adding student details
            document.add(new Paragraph("Student Index Number: "+ id));
            document.add(new Paragraph("Student Name: "+ name));
            document.add(new Paragraph("Student Class: "+clas));
            document.add(new Paragraph("Year: "+ year));
            document.add(new Paragraph("Term: "+ term));
            document.add(new Paragraph("Remarks: "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Signatures"));
            document.add(new Paragraph("Student: ..............."));
            document.add(new Paragraph("Teacher: ..............."));
            document.add(new Paragraph("Principal: ..............."));
            document.add(new Paragraph(" "));

            // Creating the table
            PdfPTable table = new PdfPTable(2); // 2 columns
            table.setWidthPercentage(100);

            // Adding table headers
            PdfPCell cell = new PdfPCell(new Phrase("No"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Subject Marks"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            // Adding table rows
            for (int i = 0; i < subj.size(); i++) {
                table.addCell(String.format("%02d", i+1) + "." + subj.get(i));
                table.addCell(marks.get(i));
            }

            // Adding total and average rows
            table.addCell("Total");
            table.addCell(total);

            table.addCell("Average");
            table.addCell(avgMark);

            table.addCell("Position");
            table.addCell(position);

            document.add(table);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	public void changePasswordStud(Event mouseEvent) throws IOException, SQLException {
		viewStudentPerformance.setVisible(false);
		
		viewNoticesA.setVisible(false);
		
		dashBoard.setVisible(true);
		
		schlimg.setVisible(false);
		greeting.setVisible(false);
		pane1.setVisible(false);
		pane2.setVisible(false);
		
		currpass.setVisible(true);
		fillcurrpass.setVisible(true);
		newpass.setVisible(true);
		fillnewpass.setVisible(true);
		updatebtn.setVisible(true);
		
		
		String sql = "select password from teacher_usernamepassword where registration_number =?";
		rst = executeQuery(sql, username1);
		if(rst.next()) {
			fillcurrpass.setText(rst.getString("password"));
		}
		
	}
	
	
	
	public void updatePassword(Event mouseEvent) throws  SQLException {
		String sql = "Update teacher_usernamepassword set password= ? where registration_number = ?";
		int k = executeUpdate(sql, fillnewpass.getText(),username1);
		showAlert(Alert.AlertType.INFORMATION, "Successful", "Updated!");
		
		sql = "select password from teacher_usernamepassword where registration_number =?";
		rst = executeQuery(sql, username1);
		if(rst.next()) {
			fillcurrpass.setText(rst.getString("password"));
		}
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		username1 = LoginController.username;
		for (int year = startYear; year <= endYear; year++) {
	        years.add(String.valueOf(year));
	    }

		resultYear.getItems().addAll(years);
		
		perfClass.getItems().addAll(clases);
		perfGrade.getItems().addAll(grade);
		perfYear.getItems().addAll(years);
		
		
		termplot.getItems().addAll(terms);
		yearplot.getItems().addAll(years);
		
		classClass.getItems().addAll(clases);
		classGrade.getItems().addAll(grade);
		classYear.getItems().addAll(years);
//		examyear.getItems().addAll(years);
//		resultGrade.getItems().addAll(grade);
		
		
		
//		createSubjectClassChoice.getItems().addAll(clases);
//		resultClass.getItems().addAll(clases);
		String grade = null;
		String sql2 = "select * from teacher_details where registration_number = ?";
		try {
			rst1 = executeQuery(sql2,  username1);
			if(rst1.next()) {
				grade = rst1.getString("current_grade");
			}
			
			if(grade.equals("1-5")) {
				resultGrade.getItems().addAll("1","2","3","4","5");
				uplosubject.getItems().addAll(grade1_5Subjects);
				classSub.getItems().addAll(grade1_5Subjects);
				
			}else {
				resultGrade.getItems().add(grade);
				uplosubject.getItems().add(rst1.getString("current_subject"));
				classSub.getItems().add(rst1.getString("current_subject"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		paymentDetailsGradeChoice.getItems().addAll(grade);
		resultTerm.getItems().addAll(terms);
		
		
		String sql1 = "SELECT * FROM teacher_details where registration_number = ?";
		try {
			rst2 = executeQuery(sql1, username1);
			String Uname = null;
			if(rst2.next()) {
				Uname = rst2.getString("full_name");
			}
			greeting.setText("Welcome to The LMS, "+ Uname);
			
			
			

			String sql = "SELECT COUNT(index_number) AS student_count FROM students_details";
			rst = executeQuery(sql);

			if (rst.next()) {
				int studentCount = rst.getInt("student_count");
				studentCountlbl.setText(Integer.toString(studentCount));
			} else {
				studentCountlbl.setText("0");
			}

			sql2 = "SELECT COUNT(registration_number) AS teacher_count FROM teacher_details";
			rst = executeQuery(sql2);

			if (rst.next()) {
				int teacherCount = rst.getInt("teacher_count");
				teacherCountlbl.setText(Integer.toString(teacherCount));
			} else {
				teacherCountlbl.setText("0");
			}
			 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
	}
	
	
	

	
	
	@FXML
	public void showPie(ActionEvent event) throws SQLException {
		
		pieChart.getData().clear();
		
		String clas = classClass.getValue();
		String year = classYear.getValue();
		String grade = classGrade.getValue();
		String SubjName = classSub.getValue();

		
		String classID = null;
		String sql0 = "SELECT classID FROM `class_details` where year=? and grade=? and class=?";
		rst2 = executeQuery(sql0,  year,grade, clas);
		if(rst2.next()){
			classID = rst2.getString("classID");
		}else {
			showAlert(Alert.AlertType.ERROR, "Error", "There aren't a class.");
			return;
		}
		
		String sql1 = "SELECT subject_id FROM `subject_details` where subject_name = ? and grade = ?";
		rst1 = executeQuery(sql1, SubjName, grade);
		String subj = null;
		if(rst1.next()) {
			subj = rst1.getString("subject_id");
		}
		sql0 = "SELECT * FROM `exam_details` where grade=? and year =?";
		rst2 = executeQuery(sql0,  grade, year);
		
		
		int marks = 0;
		
		int A = 0;
		int B = 0;
		int C = 0;
		int S = 0;
		int W = 0;
		
		while(rst2.next()) {
			
			String exam_id = rst2.getString("exam_id");
			
			
			String sql = "SELECT marks FROM `result_details` where index_number in (SELECT index_number FROM `class_studentdetails` where classID=?) and subject_id=? and exam_id = ?";
			rst = executeQuery(sql, classID,subj, exam_id);
			while(rst.next()) {
				marks = Integer.valueOf(rst.getString("marks"));
				if(marks>75) {
					A++;
				}else if(marks>65) {
					B++;
				}else if(marks>55) {
					C++;
				}else if(marks>35) {
					S++;
				}else{
					W++;
				}
				
			}  
			
		}
		
		
		
		ObservableList<PieChart.Data> result = FXCollections.observableArrayList(
					new PieChart.Data("A",A),
					new PieChart.Data("B",B),
					new PieChart.Data("C",C),
					new PieChart.Data("S",S),
					new PieChart.Data("W",W)
				);
		pieChart.getData().addAll(result);
			
        
    }
	
	@FXML
	public void plotTeach(ActionEvent event) throws SQLException {
		System.out.println("1");
		
		
		barChart2.getData().clear();
		ObservableList<XYChart.Series> subjObj = FXCollections.observableArrayList();

		String id = username1;
		String clas = perfClass.getValue();
		String year = perfYear.getValue();
		String grade = perfGrade.getValue();
		
		String classID = null;
		String sql0 = "SELECT classID FROM `class_details` where year=? and grade=? and class=?";
		rst2 = executeQuery(sql0,  year,grade, clas);
		if(rst2.next()){
			classID = rst2.getString("classID");
		}else {
			showAlert(Alert.AlertType.ERROR, "Error", "Teacher have not taught to this class.");
			return;
		}
		System.out.println("2");
		
		String subject_id = null;
		ObservableList<String> subjids = FXCollections.observableArrayList();
		ObservableList<String> examhead = FXCollections.observableArrayList();
		ObservableList<Integer> exammarks = FXCollections.observableArrayList();

		
		sql0 = "SELECT classID FROM `class_subject_teacherdetails` where registration_number=?";
		rst2 = executeQuery(sql0,  id);
		if(rst2.next()) {
			if(!classID.equals(rst2.getString("classID"))) {
				showAlert(Alert.AlertType.ERROR, "Error", "Teacher have not taught to this class.");
				return;
			}
		}
		
		sql0 = "SELECT subject_id FROM `teacher_subject` where registration_number=?";
		rst2 = executeQuery(sql0,  id);
		
		
		if(Integer.valueOf(grade) > 5) {
			System.out.println("3");
			
			if(rst2.next()){
				subject_id = rst2.getString("subject_id");
			} 
			sql0 = "SELECT * FROM `exam_details` where grade=? and year =?";
			rst2 = executeQuery(sql0,  grade, year);
			
			System.out.println("4");
			
			int avgMarks = 0;
			int count = 0;
			
			while(rst2.next()) {
				System.out.println("5");
				
				String exam_id = rst2.getString("exam_id");
				
				System.out.println(classID+" "+subject_id+" "+ exam_id);
				
				String sql = "SELECT AVG(marks) as avg FROM `result_details` where index_number in (SELECT index_number FROM `class_studentdetails` where classID=?) and subject_id=? and exam_id = ?";
				rst = executeQuery(sql, classID,subject_id, exam_id);
				if(rst.next()) {
					avgMarks = Integer.valueOf(rst.getInt("avg"));
				}
				
				exammarks.add(avgMarks);
				examhead.add(rst2.getString("grade")+"-"+rst2.getString("name"));
				
				XYChart.Series series1 = new XYChart.Series();
				series1.setName(rst2.getString("grade")+"-"+rst2.getString("name"));
				series1.getData().add(new XYChart.Data(rst2.getString("grade")+"-"+rst2.getString("name"), avgMarks));
				subjObj.add(series1);
			}
				  
			
			
		}else {
			
			while(rst2.next()) {
				System.out.println("31");
				subject_id = rst2.getString("subject_id");
				
				sql0 = "SELECT exam_id FROM `exam_details` where grade=? and year =?";
				rst = executeQuery(sql0,  grade, year);
				
				int avgMarks = 0;
				int count = 0;
				
				while(rst.next()) {
					String exam_id = rst.getString("exam_id");
					
					
					String sql = "SELECT AVG(marks) as avg FROM `result_details` where index_number in (SELECT index_number FROM `class_studentdetails` where classID=?) and subject_id=? and exam_id = ?";
					rst1 = executeQuery(sql, classID,subject_id, exam_id);
					if(rst1.next()) {
						avgMarks+= Integer.valueOf(rst1.getInt("avg"));
					}
					
					count++;
				}
				String sql1 = "SELECT subject_name FROM `subject_details` where subject_id = ?";
				rst1 = executeQuery(sql1, subject_id);
				String subjName = null;
				if(rst1.next()) {
					subjName = rst1.getString("subject_name");
				}
				XYChart.Series series1 = new XYChart.Series();
				series1.setName(subjName);
				avgMarks = avgMarks/count;
				series1.getData().add(new XYChart.Data(subjName, avgMarks));
				subjObj.add(series1);
				}  

				
			}
		
		
		
		
		for(XYChart.Series x : subjObj) {
			barChart2.getData().add(x);
		}
		
        
    }

	@FXML
	public void showplot(ActionEvent event) throws SQLException {
		System.out.println("started");
		
		
		String id = indexplot.getText();
		String term = termplot.getValue();
		String year = yearplot.getValue();
		String classID = null;
		String grade = null;
		String sql = "select classID, grade from class_details where classID in (select classID from class_studentdetails where index_number = ?) and year = ?";
		rst = executeQuery(sql, id, year);
		if(rst.next()) {
			classID = rst.getString("classID");
			grade = rst.getString("grade");
		}
		
		System.out.println(classID+ "  "+ grade);
		
		barChart.getData().clear();
		String position=null;
		sql = " select rank from (SELECT index_number, sum(marks), RANK() OVER (ORDER BY marks DESC) AS rank from result_details where index_number in ( select index_number from class_studentdetails where classID =(select classID from class_details where classID in (select classID from class_studentdetails where index_number = ?) and year = ?))  and  exam_id = (SELECT exam_id from exam_details where year =? and grade =? and name =? ) GROUP BY index_number ORDER by SUM(marks) desc) as results where index_number = ?;";
		rst = executeQuery(sql, id, year,year, grade, term, id);
		if(rst.next()) {
			position = rst.getString("rank");
		}
		System.out.println(position);
		
		
		classPlace.setText(position);
		
		ObservableList<XYChart.Series> subjObj = FXCollections.observableArrayList();

		sql = "SELECT * FROM `result_details` where index_number  = ? and exam_id = (select exam_id from exam_details where grade = ? and  year = ? and name = ?)";
		rst = executeQuery(sql, id, grade, year, term);
		while(rst.next()) {

			String sql1 = "SELECT subject_name FROM `subject_details` where subject_id = ?";
			rst1 = executeQuery(sql1, rst.getString("subject_id"));
			String subjName = null;
			if(rst1.next()) {
				subjName = rst1.getString("subject_name");
			}
			
			XYChart.Series series1 = new XYChart.Series();
			series1.setName(subjName);
			series1.getData().add(new XYChart.Data(subjName, Integer.valueOf(rst.getString("marks"))));
			subjObj.add(series1);
			
		}  
		for(XYChart.Series x : subjObj) {
			barChart.getData().add(x);
		}
        
    }

	
}