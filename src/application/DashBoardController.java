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
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DashBoardController implements Initializable {

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
	@FXML
	ChoiceBox<String> paymentyear;
	
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
	ChoiceBox<String> createClassYearChoice;
	@FXML
	ChoiceBox<String>paymenttype;
	@FXML
	ChoiceBox<String>paymenttype2;
	@FXML
	ChoiceBox<String> createClassGradeChoice;
	@FXML
	ChoiceBox<String> createClassClassChoice;
//	@FXML
//	ChoiceBox<String>stdindex;
	@FXML
	TextField createClassTeacherNO;
	@FXML
	TextField createClassStudentNO;

	@FXML
	Button createClassInsertBtn;
	@FXML
	Button createClassSubmitBtn;
	
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
	ChoiceBox<String> createSubjectYearChoice;
	@FXML
	ChoiceBox<String> createSubjectGradeChoice;
	@FXML
	ChoiceBox<String> createSubjectClassChoice;
	@FXML
	ChoiceBox<String> createSubjectSubject1Choice;
	@FXML
	ChoiceBox<String> createSubjectSubject2Choice;
	@FXML
	ChoiceBox<String> createSubjectSubject3Choice;
	
	
	@FXML
	ChoiceBox<String> classYear;
	@FXML
	ChoiceBox<String>classGrade;
	@FXML
	ChoiceBox<String>classClass;
	@FXML
	ChoiceBox<String>classSub;
	
	@FXML
	TextField createSubjectTeacherNO;
	@FXML
	TextField createSubjectStudentNO;

	@FXML
	Button createSubjectInsertBtn;
	@FXML
	Button createSubjectSubmitBtn;
	
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
	@FXML
	ChoiceBox<String> paymentDetailsGradeChoice;
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
	TableView<NoticeRecord> noticeTable;
	@FXML
	TableColumn<NoticeRecord, String> datecol;
	@FXML
	TableColumn<NoticeRecord, String> noticecol;

	ObservableList<NoticeRecord> viewNoticeList = FXCollections.observableArrayList();
	ObservableList<String> indexes = FXCollections.observableArrayList();
	ObservableList<String> regidx= FXCollections.observableArrayList();
	
	ObservableList<String> indexes1 = FXCollections.observableArrayList();
	ObservableList<String> regidx1= FXCollections.observableArrayList();
	
	ObservableList<String> indexes2 = FXCollections.observableArrayList();
	// declare all controls fro extra activity
	ObservableList<String> indexes3 = FXCollections.observableArrayList();

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

	public DashBoardController() {
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
		registerStudents.setVisible(false);
		registerTeachers.setVisible(false);
		searchStudent.setVisible(false);
		searchTeacher.setVisible(false);
		editStudent.setVisible(false);
		editTeacher.setVisible(false);
		createClassA.setVisible(false);
		createSubjectA.setVisible(false);
		scheduleExamA.setVisible(false);
		uploadResultA.setVisible(false);
		viewStudentPerformance.setVisible(false);
		viewTeacherPerformance.setVisible(false);
		viewClassPerformance.setVisible(false);
		addNoticesA.setVisible(false);
		viewNoticesA.setVisible(false);
		addPaymentsA.setVisible(false);
		viewPaymentsA.setVisible(false);
		addAchievementsA.setVisible(false);
		addSport.setVisible(false);
		dashBoard.setVisible(true);
		leavingFormA.setVisible(false);
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

	// method to handle all events in registerForm
	public void handleRegisterForms(Event mouseEvent) throws SQLException {
		// handle method for student register anchorpane
		if (mouseEvent.getSource() == registerForm || mouseEvent.getSource() == registerStudent) {
			registerStudents.setVisible(true);
			registerTeachers.setVisible(false);
			searchStudent.setVisible(false);
			searchTeacher.setVisible(false);
			editStudent.setVisible(false);
			editTeacher.setVisible(false);
			createClassA.setVisible(false);
			createSubjectA.setVisible(false);
			scheduleExamA.setVisible(false);
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(false);
			addPaymentsA.setVisible(false);
			viewPaymentsA.setVisible(false);
			addAchievementsA.setVisible(false);
			addSport.setVisible(false);
			leavingFormA.setVisible(false);
			dashBoard.setVisible(false);

			String sql = "SELECT index_number FROM students_details order by index_number desc LIMIT 1";

			rst = executeQuery(sql);

			if (rst.next()) {
				int id = rst.getInt("index_number");
				stdindexNumber.setText(Integer.toString(id + 1));
			} else {
				stdindexNumber.setText("1000");
			}
			
			
			

			sql = "SELECT registration_number FROM teacher_details order by registration_number desc LIMIT 1";

			rst = executeQuery(sql);

			if (rst.next()) {
				int id = rst.getInt("registration_number");
				techRegNumber.setText(Integer.toString(id + 1));
			} else {
				techRegNumber.setText("2000");
			}

			// Student Register Buttton implemented
		} else if (mouseEvent.getSource() == stdRegister) {
			registerStudents.setVisible(true);
			registerTeachers.setVisible(false);
			searchStudent.setVisible(false);
			searchTeacher.setVisible(false);
			editStudent.setVisible(false);
			editTeacher.setVisible(false);
			createClassA.setVisible(false);
			createSubjectA.setVisible(false);
			scheduleExamA.setVisible(false);
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(false);
			addPaymentsA.setVisible(false);
			viewPaymentsA.setVisible(false);
			addAchievementsA.setVisible(false);
			addSport.setVisible(false);
			leavingFormA.setVisible(false);
			dashBoard.setVisible(false);

			String indexNumber = stdindexNumber.getText();
			String fullName = stdfullname.getText();
			String gender = stdmaleRadioBtn.isSelected() ? "male" : (stdfemaleRadioBtn.isSelected() ? "female" : "");
			LocalDate dateOfBirth = stddateOfBirth.getValue();
			String nationalty = stdNationality.getValue();
			String religion = stdReligion.getValue();
			String contactDetails = stdcontactDetails.getText();
			String guardianName = stdguardianName.getText();
			String guardianAddres = guardianAddress.getText();
			LocalDate registrationDate = stdRegistrationDate.getValue();
			String registrationGrade = stdRegistrationGrade.getText();
			String schoolName = stdbeforeSchoolName.getText();
			if (indexNumber.isEmpty() || fullName.isEmpty() || gender.isEmpty() || dateOfBirth == null
					|| nationalty == null || religion == null || contactDetails.isEmpty() || guardianName.isEmpty()
					|| guardianAddres.isEmpty() || registrationDate == null || registrationGrade.isEmpty()
					|| schoolName.isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Error", "Please fill out all fields");
			} else {
				String sql = "INSERT INTO students_details(index_number,full_name,gender,dateOfBirth,nationality,religion,contactDetails,guardianName,guardianAddress,registrationDate,registeredGrade,beforeschoolName ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

				int k = executeUpdate(sql, indexNumber, fullName, gender, Date.valueOf(dateOfBirth), nationalty,
						religion, contactDetails, guardianName, guardianAddres, Date.valueOf(registrationDate),
						registrationGrade,  schoolName);
				
				sql = "INSERT INTO student_usernamepassword(index_number,password ) VALUES(?,?)";

				int l = executeUpdate(sql, indexNumber, "pass123");
				if (k > 0) {
					showAlert(Alert.AlertType.INFORMATION, "Success", "Student registered successfully");
					

					
					indexes2.clear();
					indexes1.clear();
					indexes.clear();
//					subjstud.getItems().clear();
//					stdindex.getItems().clear();
					stdName.getItems().clear();

					String sql1 = "SELECT index_number FROM students_details";
					try {
						rst2 = executeQuery(sql1);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						
						while(rst2.next()) {
							indexes2.add(rst2.getString("index_number"));
							
						}
//						stdindex.getItems().addAll(indexes2);
//						stdindex.setOnAction(this::fillId);
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
					
					
					
					String sql2 = "SELECT index_number FROM students_details";
					try {
						rst2 = executeQuery(sql2);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						
						while(rst2.next()) {
							indexes.add(rst2.getString("index_number"));
							
						}
						stdName.getItems().addAll(indexes);
						stdName.setOnAction(this::fillstdfield);
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					


					sql2 = "SELECT index_number FROM students_details";
					try {
						rst2 = executeQuery(sql2);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						
						while(rst2.next()) {
							indexes1.add(rst2.getString("index_number"));
							
						}
//						subjstud.getItems().addAll(indexes1);
//						subjstud.setOnAction(this::addsubjstud);
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
					
					stdindexNumber.setText("");
					stdfullname.setText("");
					stdmaleRadioBtn.setSelected(false);
					stdfemaleRadioBtn.setSelected(false);
					stddateOfBirth.setValue(null);
					stdNationality.setValue(null);
					stdReligion.setValue(null);
					stdcontactDetails.setText("");
					stdguardianName.setText("");
					guardianAddress.setText("");
					stdRegistrationDate.setValue(null);
					stdRegistrationGrade.setText("");
					stdbeforeSchoolName.setText("");
				} else {
					showAlert(Alert.AlertType.ERROR, "ERROR", "Student Registration Failed");
				}
			}
		} else if (mouseEvent.getSource() == registerTeacher) {
			registerStudents.setVisible(false);
			registerTeachers.setVisible(true);
			searchStudent.setVisible(false);
			searchTeacher.setVisible(false);
			editStudent.setVisible(false);
			editTeacher.setVisible(false);
			createClassA.setVisible(false);
			createSubjectA.setVisible(false);
			scheduleExamA.setVisible(false);
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(false);
			addPaymentsA.setVisible(false);
			viewPaymentsA.setVisible(false);
			addAchievementsA.setVisible(false);
			addSport.setVisible(false);
			dashBoard.setVisible(false);
			leavingFormA.setVisible(false);

		} else if (mouseEvent.getSource() == techRegisterBtn) {
			registerStudents.setVisible(false);
			registerTeachers.setVisible(true);
			searchStudent.setVisible(false);
			searchTeacher.setVisible(false);
			editStudent.setVisible(false);
			editTeacher.setVisible(false);
			createClassA.setVisible(false);
			createSubjectA.setVisible(false);
			scheduleExamA.setVisible(false);
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(false);
			addPaymentsA.setVisible(false);
			viewPaymentsA.setVisible(false);
			addAchievementsA.setVisible(false);
			addSport.setVisible(false);
			dashBoard.setVisible(false);
			leavingFormA.setVisible(false);

			String regNumber = techRegNumber.getText();
			String fullName = techFullName.getText();
			String nicNumber = techNICNumber.getText();
			String contactNumber = techContactNumber.getText();
			String currentGrade = techCurrentGrade.getText();
			String currentSubject = techCurrentSubject.getText();
			String maritalStatus = techMarried.isSelected() ? "married" : (techSingle.isSelected() ? "single" : "");
			String Type = teachPrimary.isSelected() ? "PRIMARY" : (teachSecondary.isSelected() ? "SECONDARY" : "");
			String gender = techMale.isSelected() ? "male" : (techFemale.isSelected() ? "female" : "");
			String qualification = techTrainedDegree.isSelected() ? "Degree"
					: (techDiploma.isSelected() ? "Diploma" : "");
			LocalDate dateOfBirth = techDateOfBirth.getValue();
			LocalDate dateForTheDivision = techDateForTheDivision.getValue();
			LocalDate dateForTheSchool = techDateForTheSchool.getValue();
			String privateAddress = techPrivateAddress.getText();

			if (regNumber.isEmpty() || fullName.isEmpty() || nicNumber.isEmpty() || contactNumber.isEmpty()
					|| currentGrade.isEmpty() || currentSubject.isEmpty() || maritalStatus.isEmpty() || gender.isEmpty()
					|| qualification.isEmpty() || dateOfBirth == null || dateForTheDivision == null
					|| dateForTheSchool == null || privateAddress.isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "ERROR", "Please fill out all fields");
			} else {
				try {
					String sql = "INSERT INTO teacher_details(registration_number,full_name,civil_status,gender,dateOfBirth,nic_Number,private_address,contact_number,current_grade,current_subject,teacher_type,division_date,school_date,category) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					int k = executeUpdate(sql, regNumber, fullName, maritalStatus, gender, Date.valueOf(dateOfBirth),
							nicNumber, privateAddress, contactNumber, currentGrade, currentSubject, qualification,
							Date.valueOf(dateForTheDivision), Date.valueOf(dateForTheSchool),Type);
					
					sql = "INSERT INTO teacher_usernamepassword(registration_number,password ) VALUES(?,?)";

					int l = executeUpdate(sql, regNumber, "pass123");
					if (Type.equals("PRIMARY")) {
						String sql1 = "INSERT INTO teacher_subject(subject_id, registration_number) VALUES(?,?)";
						
						sql = "SELECT subject_id FROM subject_details where grade in (1,2,3,4,5)";

						rst = executeQuery(sql);

						while (rst.next()) {
							int id = rst.getInt("subject_id");
							k = executeUpdate(sql1,  id, regNumber);
						}
						
						
						
					}else {
						String sql1 = "INSERT INTO teacher_subject(subject_id, registration_number) VALUES(?,?)";
						
						sql = "SELECT subject_id FROM subject_details where grade = ? and subject_name = ?";

						rst = executeQuery(sql, currentGrade, currentSubject);

						if (rst.next()) {
							int id = rst.getInt("subject_id");
							k = executeUpdate(sql1, id,regNumber);
						}
						
					}
					
					
					if (k > 0) {
						showAlert(Alert.AlertType.INFORMATION, "Success", "Teacher registered successfully");
						// Clear all input fields after successful registration
						
						regidx.clear();
						regidx1.clear();
//						teachName.getItems().clear();
//						subjTeach.getItems().clear();
						
						String sql3 = "SELECT registration_number FROM teacher_details";
						try {
							rst2 = executeQuery(sql3);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						try {
							
							while(rst2.next()) {
								regidx.add(rst2.getString("registration_number"));
								
							}
							teachName.getItems().addAll(regidx);
							teachName.setOnAction(this::fillteachfield);
						
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						sql3 = "SELECT registration_number FROM teacher_details";
						try {
							rst2 = executeQuery(sql3);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						try {
							
							while(rst2.next()) {
								regidx1.add(rst2.getString("registration_number"));
								
							}
//							subjTeach.getItems().addAll(regidx1);
//							subjTeach.setOnAction(this::addsubteach);
					
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						techRegNumber.setText("");
						techFullName.setText("");
						techNICNumber.setText("");
						techContactNumber.setText("");
						techCurrentGrade.setText("");
						techCurrentSubject.setText("");
						techMarried.setSelected(false);
						techSingle.setSelected(false);
						techMale.setSelected(false);
						techFemale.setSelected(false);
						techTrainedDegree.setSelected(false);
						techDiploma.setSelected(false);
						techDateOfBirth.setValue(null);
						techDateForTheDivision.setValue(null);
						techDateForTheSchool.setValue(null);
						techPrivateAddress.setText("");
					} else {
						showAlert(Alert.AlertType.ERROR, "ERROR", "Teacher registration failed");
					}
				} catch (SQLException e) {
					e.printStackTrace(); // Handle the exception appropriately
					showAlert(Alert.AlertType.ERROR, "ERROR",
							"An error occurred during registration: " + e.getMessage());
				}
			}

		}
	}

	// method to handle all events in searchAndEvent
	public void handleSearchAndEdit(Event mouseEvent) throws SQLException {
		if (mouseEvent.getSource() == searchAndEdit || mouseEvent.getSource() == searchStudentB
				|| mouseEvent.getSource() == searchStudent2B || mouseEvent.getSource() == searchStudent3B) {
			registerStudents.setVisible(false);
			registerTeachers.setVisible(false);
			searchStudent.setVisible(true);
			searchTeacher.setVisible(false);
			editStudent.setVisible(false);
			editTeacher.setVisible(false);
			createClassA.setVisible(false);
			createSubjectA.setVisible(false);
			scheduleExamA.setVisible(false);
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(false);
			addPaymentsA.setVisible(false);
			viewPaymentsA.setVisible(false);
			addAchievementsA.setVisible(false);
			addSport.setVisible(false);
			leavingFormA.setVisible(false);
			dashBoard.setVisible(false);

		} else if (mouseEvent.getSource() == searchstudentSearchBtn) {
			registerStudents.setVisible(false);
			registerTeachers.setVisible(false);
			searchStudent.setVisible(true);
			searchTeacher.setVisible(false);
			editStudent.setVisible(false);
			editTeacher.setVisible(false);
			createClassA.setVisible(false);
			createSubjectA.setVisible(false);
			scheduleExamA.setVisible(false);
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(false);
			addPaymentsA.setVisible(false);
			viewPaymentsA.setVisible(false);
			addAchievementsA.setVisible(false);
			addSport.setVisible(false);
			leavingFormA.setVisible(false);
			dashBoard.setVisible(false);

			String indexNumber = searchstudentSearchTextField.getText();
			if (indexNumber.isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Error", "Please Enter IndexNumber");
			} else {
				String sql = "SELECT * FROM students_details WHERE index_number = ?";
				rst = executeQuery(sql, indexNumber);
				if (rst.next()) {
					searchStudentIndexNumber.setText(rst.getString("index_number"));
					searchStudentFullName.setText(rst.getString("full_name"));
					searchStudentGender.setText(rst.getString("gender"));
					searchStudentDateOfBirth.setText(rst.getDate("dateOfBirth").toString());
					searchStudentNationality.setText(rst.getString("nationality"));
					searchStudentReligion.setText(rst.getString("religion"));
					searchStudentContactNumber.setText(rst.getString("contactDetails"));
					searchStudentGuardianName.setText(rst.getString("guardianName"));
					searchStudentGuardianAddress.setText(rst.getString("guardianAddress"));
					searchStudentRegistrationDate.setText(rst.getDate("registrationDate").toString());
					searchStudentRegisteredGrade.setText(rst.getString("registeredGrade"));
					
					searchStudentSchoolAttendedBefore.setText(rst.getString("beforeschoolName"));
				} else {
					showAlert(Alert.AlertType.ERROR, "Error", "Student not found");
				}

			}

		} else if (mouseEvent.getSource() == searchTeacherB || mouseEvent.getSource() == searchTeacher2B
				|| mouseEvent.getSource() == searchTeacher3B) {
			registerStudents.setVisible(false);
			registerTeachers.setVisible(false);
			searchStudent.setVisible(false);
			searchTeacher.setVisible(true);
			editStudent.setVisible(false);
			editTeacher.setVisible(false);
			createClassA.setVisible(false);
			createSubjectA.setVisible(false);
			scheduleExamA.setVisible(false);
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(false);
			addPaymentsA.setVisible(false);
			viewPaymentsA.setVisible(false);
			addAchievementsA.setVisible(false);
			addSport.setVisible(false);
			leavingFormA.setVisible(false);
			dashBoard.setVisible(false);

		} else if (mouseEvent.getSource() == searchTeacherSearchBtn) {
			registerStudents.setVisible(false);
			registerTeachers.setVisible(false);
			searchStudent.setVisible(false);
			searchTeacher.setVisible(true);
			editStudent.setVisible(false);
			editTeacher.setVisible(false);
			createClassA.setVisible(false);
			createSubjectA.setVisible(false);
			scheduleExamA.setVisible(false);
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(false);
			addPaymentsA.setVisible(false);
			viewPaymentsA.setVisible(false);
			addAchievementsA.setVisible(false);
			addSport.setVisible(false);
			leavingFormA.setVisible(false);
			dashBoard.setVisible(false);

			String registrationNumber = searchTeacherSearchTextField.getText();
			if (registrationNumber.isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Error", "Please Enter Registration Number");
			} else {
				String sql = "SELECT * FROM teacher_details WHERE registration_number = ?";
				rst = executeQuery(sql, registrationNumber);
				if (rst.next()) {
					searchTeacherRegistrationNumber.setText(rst.getString("registration_number"));
					searchTeacherFullName.setText(rst.getString("full_name"));
					searchTeacherCivilStatus.setText(rst.getString("civil_status"));
					searchTeacherGender.setText(rst.getString("gender"));
					searchTeacherDateOfBirth.setText(rst.getDate("dateOfBirth").toString());
					searchTeacherNICNumber.setText(rst.getString("nic_Number"));
					searchTeacherPrivateAddress.setText(rst.getString("private_address"));
					searchTeacherContactNumber.setText(rst.getString("contact_number"));
					searchTeacherCurrentGrade.setText(rst.getString("current_grade"));
					searchTeacherCurrentSubject.setText(rst.getString("category")+"-"+rst.getString("current_subject"));
					searchTeacherTeacherType.setText(rst.getString("teacher_type"));
					searchTeacherDivisionDate.setText(rst.getDate("division_date").toString());
					searchTeacherSchoolDate.setText(rst.getDate("school_date").toString());
				} else {
					showAlert(Alert.AlertType.ERROR, "Error", "Teacher not found");
				}
			}

		} else if (mouseEvent.getSource() == editStudentB || mouseEvent.getSource() == editStudent2B
				|| mouseEvent.getSource() == editStudent3B) {
			registerStudents.setVisible(false);
			registerTeachers.setVisible(false);
			searchStudent.setVisible(false);
			searchTeacher.setVisible(false);
			editStudent.setVisible(true);
			editTeacher.setVisible(false);
			createClassA.setVisible(false);
			createSubjectA.setVisible(false);
			scheduleExamA.setVisible(false);
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(false);
			addPaymentsA.setVisible(false);
			viewPaymentsA.setVisible(false);
			addAchievementsA.setVisible(false);
			addSport.setVisible(false);
			leavingFormA.setVisible(false);
			dashBoard.setVisible(false);

		} else if (mouseEvent.getSource() == editStdSearchButton) {
			registerStudents.setVisible(false);
			registerTeachers.setVisible(false);
			searchStudent.setVisible(false);
			searchTeacher.setVisible(false);
			editStudent.setVisible(true);
			editTeacher.setVisible(false);
			createClassA.setVisible(false);
			createSubjectA.setVisible(false);
			scheduleExamA.setVisible(false);
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(false);
			addPaymentsA.setVisible(false);
			viewPaymentsA.setVisible(false);
			addAchievementsA.setVisible(false);
			addSport.setVisible(false);
			leavingFormA.setVisible(false);
			dashBoard.setVisible(false);

			String indexNumber = editStudentIndexTextField.getText();
			if (indexNumber.isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Error", "Please Enter Index Number");
			} else {
				try {
					String sql = "SELECT * FROM students_details WHERE index_number = ?";
					pst = con.prepareStatement(sql);
					pst.setString(1, indexNumber);
					rst = pst.executeQuery();

					if (rst.next()) {
						// Populate the retrieved data into the corresponding fields
						editStdIndexNumber.setText(rst.getString("index_number"));
						editStdFullName.setText(rst.getString("full_name"));
						editStdContactDetails.setText(rst.getString("contactDetails"));
						editStdGuardianName.setText(rst.getString("guardianName"));
						editStdBeforeSchoolName.setText(rst.getString("beforeschoolName"));
						editStdRegistrationGrade.setText(rst.getString("registeredGrade"));
						
						// Set radio buttons based on gender
						String gender = rst.getString("gender");
						if (gender.equalsIgnoreCase("Male")) {
							editStdMaleRadioBtn.setSelected(true);
						} else if (gender.equalsIgnoreCase("Female")) {
							editStdFemaleRadioBtn.setSelected(true);
						}

						// Set date pickers
						editStdDateOfBirth.setValue(rst.getDate("dateOfBirth").toLocalDate());
						editStdRegistrationDate.setValue(rst.getDate("registrationDate").toLocalDate());

						// Set choice boxes
						editStdNationality.setValue(rst.getString("nationality"));
						editStdReligion.setValue(rst.getString("religion"));

						// Set text area
						editGuardianAddress.setText(rst.getString("guardianAddress"));
					} else {
						showAlert(Alert.AlertType.ERROR, "Error", "Student not found");
					}
				} catch (SQLException e) {
					showAlert(Alert.AlertType.ERROR, "Error", "Failed to fetch student details: " + e.getMessage());
				}

			}

		} else if (mouseEvent.getSource() == editStudentApplyBtn) {
			String editedIndex = editStdIndexNumber.getText();
			String editedFullName = editStdFullName.getText();
			String editedContactDetails = editStdContactDetails.getText();
			String editedGuardianName = editStdGuardianName.getText();
			String editedBeforeSchoolName = editStdBeforeSchoolName.getText();
			String editedRegistrationGrade = editStdRegistrationGrade.getText();
			
			// Get selected gender
			String editedGender = editStdMaleRadioBtn.isSelected() ? "male"
					: (editStdFemaleRadioBtn.isSelected() ? "female" : "");

			// Get selected dates
			LocalDate editedDateOfBirth = editStdDateOfBirth.getValue();
			LocalDate editedRegistrationDate = editStdRegistrationDate.getValue();

			// Get selected values from choice boxes
			String editedNationality = editStdNationality.getValue();
			String editedReligion = editStdReligion.getValue();

			// Get text area value
			String editedGuardianAddress = editGuardianAddress.getText();

			try {
				if (editedIndex.isEmpty() || editedFullName.isEmpty() || editedContactDetails.isEmpty()
						|| editedGuardianName.isEmpty() || editedBeforeSchoolName.isEmpty()
						|| editedRegistrationGrade.isEmpty() 
						|| editedGender.isEmpty() || editedDateOfBirth == null || editedRegistrationDate == null
						|| editedNationality.isEmpty() || editedReligion.isEmpty() || editedGuardianAddress.isEmpty()) {
					// Display error message or handle empty fields as per your application's
					// requirement
					showAlert(Alert.AlertType.ERROR, "Error", "All fields are required.");
				} else {
					String sql = "REPLACE INTO students_details "
							+ "(index_number, full_name, gender, dateOfBirth, nationality, religion, contactDetails, guardianName, "
							+ "guardianAddress, registrationDate, registeredGrade, beforeschoolName) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					int rowsAffected = executeUpdate(sql, editedIndex, editedFullName, editedGender,
							Date.valueOf(editedDateOfBirth), editedNationality, editedReligion, editedContactDetails,
							editedGuardianName, editedGuardianAddress, Date.valueOf(editedRegistrationDate),
							editedRegistrationGrade, editedBeforeSchoolName);
					if (rowsAffected > 0) {
						// Update successful
						showAlert(Alert.AlertType.INFORMATION, "Success", "Student details updated successfully.");
					} else {
						// Update failed
						showAlert(Alert.AlertType.ERROR, "Error", "Failed to update student details.");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace(); // Handle SQL exception appropriately
			}
		} else if (mouseEvent.getSource() == editStudentClearAllBtn) {
			String indexNumber = editStudentIndexTextField.getText();
			String sql = "DELETE FROM Students_details WHERE index_number = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, indexNumber);
			int rowsAffected = pst.executeUpdate();
			if (rowsAffected > 0) {
				// Update successful
				showAlert(Alert.AlertType.INFORMATION, "Success", "Student details Deleted successfully.");
				editStdIndexNumber.clear();
				editStdFullName.clear();
				editStdContactDetails.clear();
				editStdGuardianName.clear();
				editStdBeforeSchoolName.clear();
				editStdRegistrationGrade.clear();
				editStdRegistrationClass.clear();
				editStudentIndexTextField.clear();
				editStdMaleRadioBtn.setSelected(false);
				editStdFemaleRadioBtn.setSelected(false); // Clear both radio buttons
				editStdDateOfBirth.setValue(null);
				editStdRegistrationDate.setValue(null);
				editStdNationality.getSelectionModel().clearSelection(); // Clear selection from choice box
				editStdReligion.getSelectionModel().clearSelection(); // Clear selection from choice box
				editGuardianAddress.clear();
			} else {
				// Update failed
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to Delete student details.");
			}
		} else if (mouseEvent.getSource() == editStudentCancelBtn) {
			// Clear all fields
			editStdIndexNumber.clear();
			editStdFullName.clear();
			editStdContactDetails.clear();
			editStdGuardianName.clear();
			editStdBeforeSchoolName.clear();
			editStdRegistrationGrade.clear();
			editStdRegistrationClass.clear();
			editStudentIndexTextField.clear();
			editStdMaleRadioBtn.setSelected(false);
			editStdFemaleRadioBtn.setSelected(false); // Clear both radio buttons
			editStdDateOfBirth.setValue(null);
			editStdRegistrationDate.setValue(null);
			editStdNationality.getSelectionModel().clearSelection(); // Clear selection from choice box
			editStdReligion.getSelectionModel().clearSelection(); // Clear selection from choice box
			editGuardianAddress.clear();

		} else if (mouseEvent.getSource() == editTeacherB || mouseEvent.getSource() == editTeacher2B
				|| mouseEvent.getSource() == editTeacher3B) {
			registerStudents.setVisible(false);
			registerTeachers.setVisible(false);
			searchStudent.setVisible(false);
			searchTeacher.setVisible(false);
			editStudent.setVisible(false);
			editTeacher.setVisible(true);
			createClassA.setVisible(false);
			createSubjectA.setVisible(false);
			scheduleExamA.setVisible(false);
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(false);
			addPaymentsA.setVisible(false);
			viewPaymentsA.setVisible(false);
			addAchievementsA.setVisible(false);
			addSport.setVisible(false);
			leavingFormA.setVisible(false);
			dashBoard.setVisible(false);

		} else if (mouseEvent.getSource() == editTeacherSearchButton) {
			String registrationNumber = editTeacherRegistrationTextField.getText();
			if (registrationNumber.isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Error", "Please Enter Registration Number");
			} else {
				try {
					String sql = "SELECT * FROM teacher_details WHERE registration_number = ?";
					pst = con.prepareStatement(sql);
					pst.setString(1, registrationNumber);
					rst = pst.executeQuery();

					if (rst.next()) {
						// Populate the retrieved data into the corresponding fields
						editTeacherRegistrationNumber.setText(rst.getString("registration_number"));
						editTeacherFullName.setText(rst.getString("full_name"));
						editTeacherContactNumber.setText(rst.getString("contact_number"));
						editTeacherCurrentGrade.setText(rst.getString("current_grade"));
						editTeacherCurrentSubject.setText(rst.getString("current_subject"));
						editTeacherNIC.setText(rst.getString("nic_number"));
						editTeacherTextArea.setText(rst.getString("private_address"));
						// Set radio buttons based on gender
						String civilStatus = rst.getString("civil_status");
						if (civilStatus.equalsIgnoreCase("Married")) {
							editTeacherMarriedRadioBtn.setSelected(true);
						} else if (civilStatus.equalsIgnoreCase("Single")) {
							editTeacherSingleRadioBtn.setSelected(true);
						}
						// Set radio buttons based on gender
						String gender = rst.getString("gender");
						if (gender.equalsIgnoreCase("Male")) {
							editTeacherMaleRadioBtn.setSelected(true);
						} else if (gender.equalsIgnoreCase("Female")) {
							editTeacherFemaleRadioBtn.setSelected(true);
						}
						// Set radio buttons based on gender
						String techerType = rst.getString("teacher_type");
						if (techerType.equalsIgnoreCase("Degree")) {
							editTeacherTrainedRadioBtn1.setSelected(true);
						} else if (techerType.equalsIgnoreCase("Diploma")) {
							editTeacherDiplomaRadioBtn1.setSelected(true);
						}
						String cat = rst.getString("category");
						teachSecondary1.setSelected(false);
						teachPrimary1.setSelected(false);
						editTeacherCurrentGrade.setVisible(false);
						editTeacherCurrentSubject.setVisible(false);
						if (cat.equalsIgnoreCase("PRIMARY")) {
							teachPrimary1.setSelected(true);
						} else if (cat.equalsIgnoreCase("SECONDARY")) {
							editTeacherCurrentGrade.setVisible(true);
							editTeacherCurrentSubject.setVisible(true);
							teachSecondary1.setSelected(true);
						}
						
						// Set date pickers
						editTeacherDateOfBirth.setValue(rst.getDate("dateOfBirth").toLocalDate());
						editTeacherDivisionDate.setValue(rst.getDate("division_date").toLocalDate());
						editTeacherSchoolDate.setValue(rst.getDate("school_date").toLocalDate());

					} else {
						showAlert(Alert.AlertType.ERROR, "Error", "Teacher not found");
					}
				} catch (SQLException e) {
					showAlert(Alert.AlertType.ERROR, "Error", "Failed to fetch teacher details: " + e.getMessage());
				}

			}
		}
		// For applying changes to teacher details
		else if (mouseEvent.getSource() == editTeacherApplyBtn) {
			// Extracting data from fields
			String editedRegistrationNumber = editTeacherRegistrationNumber.getText();
			String editedFullName = editTeacherFullName.getText();
			String editedCivilStatus = editTeacherMarriedRadioBtn.isSelected() ? "Married"
					: editTeacherSingleRadioBtn.isSelected() ? "Single" : "";
			String editedContactNumber = editTeacherContactNumber.getText();
			String editedCurrentGrade = editTeacherCurrentGrade.getText();
			String editedCurrentSubject = editTeacherCurrentSubject.getText();
			String editedPrivateAddress = editTeacherTextArea.getText();
			String editedNIC = editTeacherNIC.getText();
			String Type = teachPrimary1.isSelected() ? "PRIMARY" : (teachSecondary1.isSelected() ? "SECONDARY" : "");
			

			// Get selected gender
			String editedGender = editTeacherMaleRadioBtn.isSelected() ? "Male"
					: editTeacherFemaleRadioBtn.isSelected() ? "Female" : "";

			// Get selected teacher type
			String editedTeacherType = editTeacherTrainedRadioBtn1.isSelected() ? "Degree"
					: editTeacherDiplomaRadioBtn1.isSelected() ? "Diploma" : "";

			// Get selected dates
			LocalDate editedDateOfBirth = editTeacherDateOfBirth.getValue();
			LocalDate editedDivisionDate = editTeacherDivisionDate.getValue();
			LocalDate editedSchoolDate = editTeacherSchoolDate.getValue();

			try {
				if (editedRegistrationNumber.isEmpty() || editedFullName.isEmpty() || editedCivilStatus.isEmpty()
						|| editedContactNumber.isEmpty() || editedCurrentGrade.isEmpty()
						|| editedCurrentSubject.isEmpty() || editedTeacherType.isEmpty()
						|| editedPrivateAddress.isEmpty() || editedNIC.isEmpty() || editedGender.isEmpty()
						|| editedDateOfBirth == null || editedDivisionDate == null || editedSchoolDate == null) {
					// Display error message or handle empty fields as per your application's
					// requirement
					showAlert(Alert.AlertType.ERROR, "Error", "All fields are required.");
				} else {
					String sql = "REPLACE INTO teacher_details "
							+ "(registration_number, full_name, civil_status, gender, dateOfBirth, nic_Number, private_address, contact_number, "
							+ "current_grade, current_subject, teacher_type, division_date, school_date,category) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
					int rowsAffected = executeUpdate(sql, editedRegistrationNumber, editedFullName, editedCivilStatus,
							editedGender, Date.valueOf(editedDateOfBirth), editedNIC, editedPrivateAddress,
							editedContactNumber, editedCurrentGrade, editedCurrentSubject, editedTeacherType,
							Date.valueOf(editedDivisionDate), Date.valueOf(editedSchoolDate), Type);
					if (rowsAffected > 0) {
						editTeacherRegistrationNumber.clear();
						editTeacherFullName.clear();
						editTeacherMarriedRadioBtn.setSelected(false);
						editTeacherSingleRadioBtn.setSelected(false);
						editTeacherMaleRadioBtn.setSelected(false);
						editTeacherFemaleRadioBtn.setSelected(false);
						editTeacherNIC.clear();
						editTeacherTextArea.clear();
						editTeacherContactNumber.clear();
						editTeacherCurrentGrade.clear();
						editTeacherCurrentSubject.clear();
						editTeacherTrainedRadioBtn1.setSelected(false);
						editTeacherDiplomaRadioBtn1.setSelected(false);
						editTeacherDateOfBirth.setValue(null);
						editTeacherDivisionDate.setValue(null);
						editTeacherSchoolDate.setValue(null);
						showAlert(Alert.AlertType.INFORMATION, "Success", "Teacher details updated successfully.");
					} else {
						// Update failed
						showAlert(Alert.AlertType.ERROR, "Error", "Failed to update teacher details.");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace(); // Handle SQL exception appropriately
			}
		}

		// For clearing all teacher fields
		else if (mouseEvent.getSource() == editTeacherClearAllBtn) {
			String registrationNumber = editTeacherRegistrationTextField.getText();
			String sql = "DELETE FROM teacher_details WHERE registration_number = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, registrationNumber);
			int rowsAffected = pst.executeUpdate();
			if (rowsAffected > 0) {
				// Update successful
				showAlert(Alert.AlertType.INFORMATION, "Success", "teacher details Deleted successfully.");
				// Clear all fields
				editTeacherRegistrationNumber.clear();
				editTeacherFullName.clear();
				editTeacherMarriedRadioBtn.setSelected(false);
				editTeacherSingleRadioBtn.setSelected(false);
				editTeacherMaleRadioBtn.setSelected(false);
				editTeacherFemaleRadioBtn.setSelected(false);
				editTeacherNIC.clear();
				editTeacherTextArea.clear();
				editTeacherContactNumber.clear();
				editTeacherCurrentGrade.clear();
				editTeacherCurrentSubject.clear();
				editTeacherTrainedRadioBtn.setSelected(false);
				editTeacherDiplomaRadioBtn.setSelected(false);
				editTeacherDateOfBirth.setValue(null);
				editTeacherDivisionDate.setValue(null);
				editTeacherSchoolDate.setValue(null);
			} else {
				// Update failed
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to Delete Teacher details.");
			}

		}

		// For canceling the edit teacher operation
		else if (mouseEvent.getSource() == editTeacherCancelBtn) {
			// Clear all fields
			editTeacherRegistrationNumber.clear();
			editTeacherFullName.clear();
			editTeacherMarriedRadioBtn.setSelected(false);
			editTeacherSingleRadioBtn.setSelected(false);
			editTeacherMaleRadioBtn.setSelected(false);
			editTeacherFemaleRadioBtn.setSelected(false);
			editTeacherNIC.clear();
			editTeacherTextArea.clear();
			editTeacherContactNumber.clear();
			editTeacherCurrentGrade.clear();
			editTeacherCurrentSubject.clear();
			editTeacherTrainedRadioBtn.setSelected(false);
			editTeacherDiplomaRadioBtn.setSelected(false);
			editTeacherDateOfBirth.setValue(null);
			editTeacherDivisionDate.setValue(null);
			editTeacherSchoolDate.setValue(null);
		}
	}
	
	// method to handle all invents in createClass
	public void handleCreateClass(Event mouseEvent) throws SQLException {
		if (mouseEvent.getSource() == createClass) {
			registerStudents.setVisible(false);
			registerTeachers.setVisible(false);
			searchStudent.setVisible(false);
			searchTeacher.setVisible(false);
			editStudent.setVisible(false);
			editTeacher.setVisible(false);
			createClassA.setVisible(true);
			createSubjectA.setVisible(false);
			scheduleExamA.setVisible(false);
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(false);
			addPaymentsA.setVisible(false);
			viewPaymentsA.setVisible(false);
			addAchievementsA.setVisible(false);
			addSport.setVisible(false);
			leavingFormA.setVisible(false);
			dashBoard.setVisible(false);

		} else if (mouseEvent.getSource() == createClassInsertBtn) {
			String year = createClassYearChoice.getValue();
			String grade = createClassGradeChoice.getValue();
			String classs = createClassClassChoice.getValue();
			String std = createClassStudentNO.getText();
			
			
			if(manualButton.isSelected()) {
				String sql1 = "SELECT * FROM class_details WHERE class =? and grade = ? and year = ?";
				rst = executeQuery(sql1, classs, grade, year);
				if (rst.next()) {
					int classID = rst.getInt("classID");
					
					sql1 = "Select * from class_studentdetails where index_number = ? and classID = ?;";
					rst = executeQuery(sql1, std,classID);
					if (!rst.next()) {
						
						sql1 = "INSERT INTO class_studentdetails(classID ,index_number) VALUES (?,?);";
						int k = executeUpdate(sql1, classID, std);
					
					}
					
					
				}else {
					sql1 = "INSERT INTO class_details(year , grade , class ) VALUES (?,?,?);";
					int k = executeUpdate(sql1, year, grade, classs);
					
					sql1 = "SELECT * FROM class_details WHERE class =? and grade = ? and year = ?";
					rst = executeQuery(sql1, classs, grade, year);
					if (rst.next()) {
						int classID = rst.getInt("classID");
						
						sql1 = "Select * from class_studentdetails where index_number = ? and classID = ?;";
						rst = executeQuery(sql1, std,classID);
						if (!rst.next()) {
							
							sql1 = "INSERT INTO class_studentdetails(classID ,index_number) VALUES (?,?);";
							k = executeUpdate(sql1, classID, std);
						
						}
					}
				}
				
					
			}else if(uploadButton.isSelected()) {
				int classID = 0;
				String sql1 = "SELECT * FROM class_details WHERE class =? and grade = ? and year = ?";
				rst = executeQuery(sql1, classs, grade, year);
				if (rst.next()) {
					classID = rst.getInt("classID");				
				}else {
					sql1 = "INSERT INTO class_details(year , grade , class ) VALUES (?,?,?);";
					int k = executeUpdate(sql1, year, grade, classs);
					
					sql1 = "SELECT * FROM class_details WHERE class =? and grade = ? and year = ?";
					rst = executeQuery(sql1, classs, grade, year);
					if (rst.next()) {
						classID = rst.getInt("classID");	
					}
				}
				
				for(String x: indexes3) {
					sql1 = "Select * from class_studentdetails where index_number = ? and classID = ?;";
					rst = executeQuery(sql1, x,classID);
					if (!rst.next()) {
						
						sql1 = "INSERT INTO class_studentdetails(classID ,index_number) VALUES (?,?);";
						int k = executeUpdate(sql1, classID, x);
					
					}
				}
				
			
			}
				
			
			
			
		} else if (mouseEvent.getSource() == createClassteacherInsertBtn1) {
			String year = createClassYearChoice.getValue();
			String grade = createClassGradeChoice.getValue();
			String classs = createClassClassChoice.getValue();
			String teacher = teachName.getValue();
//			String std = createClassStudentNO.getText();
			
			
			if (year.isEmpty() || grade.isEmpty() || classs.isEmpty() || teacher.isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Error", "Invalid teacherId");
			} else {
				
				String sql1 = "SELECT * FROM class_details WHERE class =? and grade = ? and year = ?";
				rst = executeQuery(sql1, classs, grade, year);
				if (rst.next()) {
					String classID = rst.getString("classID");
					
					sql1 = "Select * from class_teacher where classID = ?;";
					rst = executeQuery(sql1,classID);
					if (rst.next()) {
						sql1 = "Update  class_teacher set registration_number = ? where classID =?;";
						int k = executeUpdate(sql1, teacher, classID);
						showAlert(Alert.AlertType.INFORMATION, "Successful", "Updated teacherId");
					}else {
						sql1 = "INSERT INTO class_teacher(classID ,registration_number) VALUES (?,?);";
						int k = executeUpdate(sql1, classID, teacher);
						showAlert(Alert.AlertType.INFORMATION, "Successful", "Inserted teacherId");
					
					}
					
					
				}else {
					sql1 = "INSERT INTO class_details(year , grade , class ) VALUES (?,?,?);";
					int k = executeUpdate(sql1, year, grade, classs);
					
					sql1 = "SELECT * FROM class_details WHERE class =? and grade = ? and year = ?";
					rst = executeQuery(sql1, classs, grade, year);
					if (rst.next()) {
						String classID = rst.getString("classID");
						
						sql1 = "Select * from class_teacher where classID = ?;";
						rst = executeQuery(sql1, classID);
						if (rst.next()) {
							sql1 = "Update  class_teacher set registration_number = ? where classID =?;";
							k = executeUpdate(sql1, teacher, classID);
							showAlert(Alert.AlertType.INFORMATION, "Successful", "Updated teacherId");
						}else {
							sql1 = "INSERT INTO class_teacher(classID ,registration_number) VALUES (?,?);";
							k = executeUpdate(sql1, classID, teacher);
							showAlert(Alert.AlertType.INFORMATION, "Successful", "Inserted teacherId");
						
						}
				} 
				
					teachName.setValue(null);
				}
			}
		}

					
				
			
		
		
	}

	// method to handle all invents in createSubjects
	public void handleCreateSubject(Event mouseEvent) throws SQLException {
		if (mouseEvent.getSource() == createSubject) {
			registerStudents.setVisible(false);
			registerTeachers.setVisible(false);
			searchStudent.setVisible(false);
			searchTeacher.setVisible(false);
			editStudent.setVisible(false);
			editTeacher.setVisible(false);
			createClassA.setVisible(false);
			createSubjectA.setVisible(true);
			scheduleExamA.setVisible(false);
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(false);
			addPaymentsA.setVisible(false);
			viewPaymentsA.setVisible(false);
			addAchievementsA.setVisible(false);
			addSport.setVisible(false);
			leavingFormA.setVisible(false);
			dashBoard.setVisible(false);

		} else if (mouseEvent.getSource() == createSubjectInsertBtn) {
			
			
			String clases = createSubjectClassChoice.getValue();
			String year = createSubjectYearChoice.getValue();
			String examGradeField = createSubjectGradeChoice.getValue();
			String classID = null;
			
			String sql3 = "Select classID from class_details where year = ? and grade = ? and class=?";
			rst2 = executeQuery(sql3, year, examGradeField, clases);
			
			if(rst2.next()) {
				classID=rst2.getString("classID");
				
			}
			
		
			if(Integer.valueOf(examGradeField) > 9) {
				
				String sql1 = "INSERT INTO class_subject_teacherdetails(classID ,registration_number) VALUES (?,?);";
				int k = executeUpdate(sql1, classID, subjTeach1.getValue());
				
				sql1 = "INSERT INTO class_subject_teacherdetails(classID ,registration_number) VALUES (?,?);";
				k = executeUpdate(sql1, classID, subjTeach2.getValue());
				
				sql1 = "INSERT INTO class_subject_teacherdetails(classID ,registration_number) VALUES (?,?);";
				k = executeUpdate(sql1, classID, subjTeach3.getValue());
				
				sql1 = "INSERT INTO class_subject_teacherdetails(classID ,registration_number) VALUES (?,?);";
				k = executeUpdate(sql1, classID, subjTeach4.getValue());
				
				sql1 = "INSERT INTO class_subject_teacherdetails(classID ,registration_number) VALUES (?,?);";
				k = executeUpdate(sql1, classID, subjTeach5.getValue());
				
				sql1 = "INSERT INTO class_subject_teacherdetails(classID ,registration_number) VALUES (?,?);";
				k = executeUpdate(sql1, classID, subjTeach6.getValue());
				
								
				
			}else if(Integer.valueOf(examGradeField) > 5){
				String sql1 = "INSERT INTO class_subject_teacherdetails(classID ,registration_number) VALUES (?,?);";
				int k = executeUpdate(sql1, classID, subjTeach1.getValue());
				
				sql1 = "INSERT INTO class_subject_teacherdetails(classID ,registration_number) VALUES (?,?);";
				k = executeUpdate(sql1, classID, subjTeach2.getValue());
				
				sql1 = "INSERT INTO class_subject_teacherdetails(classID ,registration_number) VALUES (?,?);";
				k = executeUpdate(sql1, classID, subjTeach3.getValue());
				
				sql1 = "INSERT INTO class_subject_teacherdetails(classID ,registration_number) VALUES (?,?);";
				k = executeUpdate(sql1, classID, subjTeach4.getValue());
				
				sql1 = "INSERT INTO class_subject_teacherdetails(classID ,registration_number) VALUES (?,?);";
				k = executeUpdate(sql1, classID, subjTeach5.getValue());
				
				sql1 = "INSERT INTO class_subject_teacherdetails(classID ,registration_number) VALUES (?,?);";
				k = executeUpdate(sql1, classID, subjTeach6.getValue());
				
				sql1 = "INSERT INTO class_subject_teacherdetails(classID ,registration_number) VALUES (?,?);";
				k = executeUpdate(sql1, classID, subjTeach7.getValue());
				
				sql1 = "INSERT INTO class_subject_teacherdetails(classID ,registration_number) VALUES (?,?);";
				k = executeUpdate(sql1, classID, subjTeach8.getValue());
				
				sql1 = "INSERT INTO class_subject_teacherdetails(classID ,registration_number) VALUES (?,?);";
				k = executeUpdate(sql1, classID, subjTeach9.getValue());
				
				sql1 = "INSERT INTO class_subject_teacherdetails(classID ,registration_number) VALUES (?,?);";
				k = executeUpdate(sql1, classID, subjTeach10.getValue());
				
				sql1 = "INSERT INTO class_subject_teacherdetails(classID ,registration_number) VALUES (?,?);";
				k = executeUpdate(sql1, classID, subjTeach11.getValue());

				sql1 = "INSERT INTO class_subject_teacherdetails(classID ,registration_number) VALUES (?,?);";
				k = executeUpdate(sql1, classID, subjTeach12.getValue());
				
			}
			else {
				String sql1 = "INSERT INTO class_subject_teacherdetails(classID ,registration_number) VALUES (?,?);";
				int k = executeUpdate(sql1, classID, subjTeach1.getValue());
				
				sql1 = "INSERT INTO class_subject_teacherdetails(classID ,registration_number) VALUES (?,?);";
				k = executeUpdate(sql1, classID, subjTeach2.getValue());
				
				sql1 = "INSERT INTO class_subject_teacherdetails(classID ,registration_number) VALUES (?,?);";
				k = executeUpdate(sql1, classID, subjTeach3.getValue());
				
				sql1 = "INSERT INTO class_subject_teacherdetails(classID ,registration_number) VALUES (?,?);";
				k = executeUpdate(sql1, classID, subjTeach4.getValue());
				
				sql1 = "INSERT INTO class_subject_teacherdetails(classID ,registration_number) VALUES (?,?);";
				k = executeUpdate(sql1, classID, subjTeach5.getValue());
				
				sql1 = "INSERT INTO class_subject_teacherdetails(classID ,registration_number) VALUES (?,?);";
				k = executeUpdate(sql1, classID, subjTeach6.getValue());
			}
		
		}
	}

		
	

	
	
	
	// method to handle all invents in scheduleExam
	public void handleScheduleExams(Event mouseEvent) {
		if (mouseEvent.getSource() == scheduleExam) {
			registerStudents.setVisible(false);
			registerTeachers.setVisible(false);
			searchStudent.setVisible(false);
			searchTeacher.setVisible(false);
			editStudent.setVisible(false);
			editTeacher.setVisible(false);
			createClassA.setVisible(false);
			createSubjectA.setVisible(false);
			scheduleExamA.setVisible(true);
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(false);
			addPaymentsA.setVisible(false);
			viewPaymentsA.setVisible(false);
			addAchievementsA.setVisible(false);
			addSport.setVisible(false);
			leavingFormA.setVisible(false);
			dashBoard.setVisible(false);

		} else if(mouseEvent.getSource() == examAddButton){
//			System.out.println("Working..");
			String examNameField = examName1.getValue();
			if (examNameField.isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Error", "Please Enter Exam Name");
				return;
			}
			// get exam type using radio buttons
			String examType = examMonthlyRadio.isSelected() ? "Monthly" : examTermlyRadio.isSelected() ? "Termly" : "";
			// if exam type is empty, show an error message
			if (examType.isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Error", "Please select exam type");
				return;
			}
			// get exam grade from checkbox
			String year = examyear.getValue();
			String examGradeField = examGrade.getValue();
			// if exam grade is empty, show an error message
			if (examGradeField.isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Error", "Please select exam grade");
				return;
			}else if(Integer.valueOf(examGradeField) > 9) {
//				System.out.println(examGradeField);
				
				int numberOfsubj = 9;
				String[] subjectNames = { examSubject1.getText(), examSubject2.getText(), examSubject3.getText(),
						examSubject4.getText(), examSubject5.getText(), examSubject6.getText(), examSubject7.getText(),
						examSubject8.getText(), examSubject9.getText()};
				LocalDate[] subjectDates = { examDate1.getValue(), examDate2.getValue(), examDate3.getValue(),
						examDate4.getValue(), examDate5.getValue(), examDate6.getValue(), examDate7.getValue(),
						examDate8.getValue(), examDate9.getValue()};
				String[] subjectTimes = { examTime1.getText(), examTime2.getText(), examTime3.getText(),
						examTime4.getText(), examTime5.getText(), examTime6.getText(), examTime7.getText(),
						examTime8.getText(), examTime9.getText() };
				String additionalInfo = examAdditionalDetails.getText();
				// if additional information is empty, add a default message
				if (additionalInfo.isEmpty()) {
					additionalInfo = "No additional information";
				}
				try {
					String sql = "INSERT INTO exam_details(name , grade , type, year, additional_details) VALUES(?,?,?,?,?)";
					int k = executeUpdate(sql, examNameField, examGradeField, examType,year, additionalInfo);

					if (k > 0) {
						// get exam detals from the database
						String sql1 = "SELECT exam_id FROM exam_details WHERE name = ? AND grade = ? AND type = ? AND year = ? AND additional_details = ?";
						rst = executeQuery(sql1, examNameField, examGradeField, examType, year, additionalInfo);
						// get the exam ID
						if (rst.next()) {
							int examID = rst.getInt("exam_id");
							// add subject details to the database
							for (int i = 0; i < numberOfsubj; i++) {
								if (!subjectNames[i].isEmpty() && subjectDates[i] != null && subjectTimes[i] != null) {
									String sql2 = "INSERT INTO exam_subjectdetails(exam_id, subject_name, exam_date, time) VALUES(?,?,?,?)";
									k = executeUpdate(sql2, examID, subjectNames[i], Date.valueOf(subjectDates[i]),
											subjectTimes[i]);
									
								}
							}
							examName1.setValue(null);
							examMonthlyRadio.setSelected(false);
//							examGrade.setValue(null);
							examSubject1.setText("");
							examSubject2.setText("");
							examSubject3.setText("");
							examSubject4.setText("");
							examSubject5.setText("");
							examSubject6.setText("");
							examSubject7.setText("");
							examSubject8.setText("");
							examSubject9.setText("");
							examSubject10.setText("");
							examSubject11.setText("");
							examSubject12.setText("");
							examyear.setValue(null);
							examDate1.setValue(null);
							examDate2.setValue(null);
							examDate3.setValue(null);
							examDate4.setValue(null);
							examDate5.setValue(null);
							examDate6.setValue(null);
							examDate7.setValue(null);
							examDate8.setValue(null);
							examDate9.setValue(null);
							examDate10.setValue(null);
							examDate11.setValue(null);
							examDate12.setValue(null);
							examTime1.setText("");
							examTime2.setText("");
							examTime3.setText("");
							examTime4.setText("");
							examTime5.setText("");
							examTime6.setText("");
							examTime7.setText("");
							examTime8.setText("");
							examTime9.setText("");
							examTime10.setText("");
							examTime11.setText("");
							examTime12.setText("");
						}
					} else {
						showAlert(Alert.AlertType.ERROR, "Error", "Failed to add exam details");
					}

				} catch (SQLException e) {
					showAlert(Alert.AlertType.ERROR, "Error", "Failed to add exam details: " + e.getMessage());
				}

			}else if(Integer.valueOf(examGradeField) > 5) {
				int numberOfsubj = 12;
				String[] subjectNames = { examSubject1.getText(), examSubject2.getText(), examSubject3.getText(),
						examSubject4.getText(), examSubject5.getText(), examSubject6.getText(), examSubject7.getText(),
						examSubject8.getText(), examSubject9.getText(), examSubject10.getText(), examSubject11.getText(),
						examSubject12.getText() };
				LocalDate[] subjectDates = { examDate1.getValue(), examDate2.getValue(), examDate3.getValue(),
						examDate4.getValue(), examDate5.getValue(), examDate6.getValue(), examDate7.getValue(),
						examDate8.getValue(), examDate9.getValue(), examDate10.getValue(), examDate11.getValue(),
						examDate12.getValue() };
				String[] subjectTimes = { examTime1.getText(), examTime2.getText(), examTime3.getText(),
						examTime4.getText(), examTime5.getText(), examTime6.getText(), examTime7.getText(),
						examTime8.getText(), examTime9.getText(), examTime10.getText(), examTime11.getText(),
						examTime12.getText() };
				String additionalInfo = examAdditionalDetails.getText();
				// if additional information is empty, add a default message
				if (additionalInfo.isEmpty()) {
					additionalInfo = "No additional information";
				}
				try {
					String sql = "INSERT INTO exam_details(name , grade , type,year, additional_details) VALUES(?,?,?,?,?)";
					int k = executeUpdate(sql, examNameField, examGradeField, examType,year, additionalInfo);

					if (k > 0) {
						// get exam detals from the database
						String sql1 = "SELECT exam_id FROM exam_details WHERE name = ? AND grade = ? AND type = ? AND year = ? AND additional_details = ?";
						rst = executeQuery(sql1, examNameField, examGradeField, examType,year, additionalInfo);
						// get the exam ID
						if (rst.next()) {
							int examID = rst.getInt("exam_id");
							// add subject details to the database
							for (int i = 0; i < numberOfsubj; i++) {
								if (!subjectNames[i].isEmpty() && subjectDates[i] != null && subjectTimes[i] != null) {
									String sql2 = "INSERT INTO exam_subjectdetails(exam_id, subject_name, exam_date, time) VALUES(?,?,?,?)";
									k = executeUpdate(sql2, examID, subjectNames[i], Date.valueOf(subjectDates[i]),
											subjectTimes[i]);
									
									
								}
							}
							examName1.setValue(null);
							examMonthlyRadio.setSelected(false);
//							examGrade.setValue(null);
							examSubject1.setText("");
							examSubject2.setText("");
							examSubject3.setText("");
							examSubject4.setText("");
							examSubject5.setText("");
							examSubject6.setText("");
							examSubject7.setText("");
							examSubject8.setText("");
							examSubject9.setText("");
							examSubject10.setText("");
							examSubject11.setText("");
							examSubject12.setText("");
							examDate1.setValue(null);
							examDate2.setValue(null);
							examDate3.setValue(null);
							examDate4.setValue(null);
							examDate5.setValue(null);
							examDate6.setValue(null);
							examDate7.setValue(null);
							examDate8.setValue(null);
							examDate9.setValue(null);
							examDate10.setValue(null);
							examDate11.setValue(null);
							examDate12.setValue(null);
							examTime1.setText("");
							examTime2.setText("");
							examTime3.setText("");
							examTime4.setText("");
							examTime5.setText("");
							examTime6.setText("");
							examTime7.setText("");
							examTime8.setText("");
							examTime9.setText("");
							examTime10.setText("");
							examTime11.setText("");
							examTime12.setText("");
						}
					} else {
						showAlert(Alert.AlertType.ERROR, "Error", "Failed to add exam details");
					}

				} catch (SQLException e) {
					showAlert(Alert.AlertType.ERROR, "Error", "Failed to add exam details: " + e.getMessage());
				}

			}else {
				int numberOfsubj = 6;
				String[] subjectNames = { examSubject1.getText(), examSubject2.getText(), examSubject3.getText(),
						examSubject4.getText(), examSubject5.getText(), examSubject6.getText() };
				LocalDate[] subjectDates = { examDate1.getValue(), examDate2.getValue(), examDate3.getValue(),
						examDate4.getValue(), examDate5.getValue(), examDate6.getValue() };
				String[] subjectTimes = { examTime1.getText(), examTime2.getText(), examTime3.getText(),
						examTime4.getText(), examTime5.getText(), examTime6.getText()};
				String additionalInfo = examAdditionalDetails.getText();
				// if additional information is empty, add a default message
				if (additionalInfo.isEmpty()) {
					additionalInfo = "No additional information";
				}
				try {
					String sql = "INSERT INTO exam_details(name , grade , type,year, additional_details) VALUES(?,?,?,?,?)";
					int k = executeUpdate(sql, examNameField, examGradeField, examType,year, additionalInfo);

					if (k > 0) {
						// get exam detals from the database
						String sql1 = "SELECT exam_id FROM exam_details WHERE name = ? AND grade = ? AND type = ? AND year = ? AND additional_details = ?";
						rst = executeQuery(sql1, examNameField, examGradeField, examType,year, additionalInfo);
						// get the exam ID
						if (rst.next()) {
							int examID = rst.getInt("exam_id");
							// add subject details to the database
							for (int i = 0; i < numberOfsubj; i++) {
								if (!subjectNames[i].isEmpty() && subjectDates[i] != null && subjectTimes[i] != null) {
									String sql2 = "INSERT INTO exam_subjectdetails(exam_id, subject_name, exam_date, time) VALUES(?,?,?,?)";
									k = executeUpdate(sql2, examID, subjectNames[i], Date.valueOf(subjectDates[i]),
											subjectTimes[i]);
									
									
								}
							}
							examName1.setValue(null);
							examMonthlyRadio.setSelected(false);
//							examGrade.setValue(null);
							examSubject1.setText("");
							examSubject2.setText("");
							examSubject3.setText("");
							examSubject4.setText("");
							examSubject5.setText("");
							examSubject6.setText("");
							examSubject7.setText("");
							examSubject8.setText("");
							examSubject9.setText("");
							examSubject10.setText("");
							examSubject11.setText("");
							examSubject12.setText("");
							examDate1.setValue(null);
							examDate2.setValue(null);
							examDate3.setValue(null);
							examDate4.setValue(null);
							examDate5.setValue(null);
							examDate6.setValue(null);
							examDate7.setValue(null);
							examDate8.setValue(null);
							examDate9.setValue(null);
							examDate10.setValue(null);
							examDate11.setValue(null);
							examDate12.setValue(null);
							examTime1.setText("");
							examTime2.setText("");
							examTime3.setText("");
							examTime4.setText("");
							examTime5.setText("");
							examTime6.setText("");
							examTime7.setText("");
							examTime8.setText("");
							examTime9.setText("");
							examTime10.setText("");
							examTime11.setText("");
							examTime12.setText("");
						}
					} else {
						showAlert(Alert.AlertType.ERROR, "Error", "Failed to add exam details");
					}

				} catch (SQLException e) {
					showAlert(Alert.AlertType.ERROR, "Error", "Failed to add exam details: " + e.getMessage());
				}
			}
			
		}
	}

	// method to handle all invents in result
	public void handleResult(Event mouseEvent) {
		if (mouseEvent.getSource() == uploadResult) {
			registerStudents.setVisible(false);
			registerTeachers.setVisible(false);
			searchStudent.setVisible(false);
			searchTeacher.setVisible(false);
			editStudent.setVisible(false);
			editTeacher.setVisible(false);
			createClassA.setVisible(false);
			createSubjectA.setVisible(false);
			scheduleExamA.setVisible(false);
			uploadResultA.setVisible(true);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(false);
			addPaymentsA.setVisible(false);
			viewPaymentsA.setVisible(false);
			addAchievementsA.setVisible(false);
			addSport.setVisible(false);
			leavingFormA.setVisible(false);
			dashBoard.setVisible(false);

		}
	}

	// method to handle all invents in performance
	public void handlePerformance(Event mouseEvent) {
		if (mouseEvent.getSource() == viewPerfomance || mouseEvent.getSource() == viewStudentPerformanceB
				|| mouseEvent.getSource() == viewStudentPerformance2B) {
			registerStudents.setVisible(false);
			registerTeachers.setVisible(false);
			searchStudent.setVisible(false);
			searchTeacher.setVisible(false);
			editStudent.setVisible(false);
			editTeacher.setVisible(false);
			createClassA.setVisible(false);
			createSubjectA.setVisible(false);
			scheduleExamA.setVisible(false);
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(true);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(false);
			addPaymentsA.setVisible(false);
			viewPaymentsA.setVisible(false);
			addAchievementsA.setVisible(false);
			addSport.setVisible(false);
			leavingFormA.setVisible(false);
			dashBoard.setVisible(false);

		} else if (mouseEvent.getSource() == viewTeacherPerformancesB
				|| mouseEvent.getSource() == viewTeacherPerformances2B) {
			registerStudents.setVisible(false);
			registerTeachers.setVisible(false);
			searchStudent.setVisible(false);
			searchTeacher.setVisible(false);
			editStudent.setVisible(false);
			editTeacher.setVisible(false);
			createClassA.setVisible(false);
			createSubjectA.setVisible(false);
			scheduleExamA.setVisible(false);
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(true);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(false);
			addPaymentsA.setVisible(false);
			viewPaymentsA.setVisible(false);
			addAchievementsA.setVisible(false);
			addSport.setVisible(false);
			leavingFormA.setVisible(false);
			dashBoard.setVisible(false);

		} else if (mouseEvent.getSource() == viewClassPerformancesB
				|| mouseEvent.getSource() == viewClassPerformances2B) {
			registerStudents.setVisible(false);
			registerTeachers.setVisible(false);
			searchStudent.setVisible(false);
			searchTeacher.setVisible(false);
			editStudent.setVisible(false);
			editTeacher.setVisible(false);
			createClassA.setVisible(false);
			createSubjectA.setVisible(false);
			scheduleExamA.setVisible(false);
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(true);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(false);
			addPaymentsA.setVisible(false);
			viewPaymentsA.setVisible(false);
			addAchievementsA.setVisible(false);
			addSport.setVisible(false);
			leavingFormA.setVisible(false);
			dashBoard.setVisible(false);

		}
	}

	// method to handle all invents in notices
	public void handleNotices(Event mouseEvent) {
		if (mouseEvent.getSource() == addNotice || mouseEvent.getSource() == notices) {
			registerStudents.setVisible(false);
			registerTeachers.setVisible(false);
			searchStudent.setVisible(false);
			searchTeacher.setVisible(false);
			editStudent.setVisible(false);
			editTeacher.setVisible(false);
			createClassA.setVisible(false);
			createSubjectA.setVisible(false);
			scheduleExamA.setVisible(false);
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(true);
			viewNoticesA.setVisible(false);
			addPaymentsA.setVisible(false);
			viewPaymentsA.setVisible(false);
			addAchievementsA.setVisible(false);
			addSport.setVisible(false);
			leavingFormA.setVisible(false);
			dashBoard.setVisible(false);

		} else if (mouseEvent.getSource() == viewNotices) {
			registerStudents.setVisible(false);
			registerTeachers.setVisible(false);
			searchStudent.setVisible(false);
			searchTeacher.setVisible(false);
			editStudent.setVisible(false);
			editTeacher.setVisible(false);
			createClassA.setVisible(false);
			createSubjectA.setVisible(false);
			scheduleExamA.setVisible(false);
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(true);
			addPaymentsA.setVisible(false);
			viewPaymentsA.setVisible(false);
			addAchievementsA.setVisible(false);
			addSport.setVisible(false);
			leavingFormA.setVisible(false);
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

	// method to handle all invents in payment
	public void handlePayments(Event mouseEvent) throws SQLException, IOException {
		if (mouseEvent.getSource() == addPayments || mouseEvent.getSource() == payments) {
			registerStudents.setVisible(false);
			registerTeachers.setVisible(false);
			searchStudent.setVisible(false);
			searchTeacher.setVisible(false);
			editStudent.setVisible(false);
			editTeacher.setVisible(false);
			createClassA.setVisible(false);
			createSubjectA.setVisible(false);
			scheduleExamA.setVisible(false);
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(false);
			addPaymentsA.setVisible(true);
			viewPaymentsA.setVisible(false);
			addAchievementsA.setVisible(false);
			addSport.setVisible(false);
			leavingFormA.setVisible(false);
			dashBoard.setVisible(false);

		} else if (mouseEvent.getSource() == paymentDetailsPayBtn) {

			String indexNumber = paymentDetailsIndexNumber.getText();
			String paymentName = paymentDetailsPaymentName.getText();
			LocalDate date = paymentDetailsPaymentDate.getValue();
			String grade = paymentDetailsGradeChoice.getValue();
			String amount = paymentDetailsAmout.getText();
			String note = paymentDetailsPaymentSpecialNote.getText();

			String sql = "SELECT * FROM students_details WHERE index_number = ?";
			rst = executeQuery(sql, indexNumber);
			if (rst.next()) {
				String sql3 = "INSERT INTO payment_details(payment_name,payment_date,payment_grade,payment_amount,special_note,index_number) VALUES (?,?,?,?,?,?)";
				int k = executeUpdate(sql3, paymentName, Date.valueOf(date), grade,  amount, note, indexNumber);
				if (k > 0) {
					String sql6 = "SELECT invoice_number FROM payment_details WHERE payment_name = ? AND payment_date = ?  AND payment_grade = ?  AND payment_amount = ? AND special_note = ? AND index_number = ?";
					rst1 = executeQuery(sql6, paymentName, Date.valueOf(date), grade, amount, note, indexNumber);
					if (rst1.next()) {
						generatePymentSlip(indexNumber, rst1.getString("invoice_number"), paymentName, date.toString(),
								grade, amount, note);
						paymentDetailsIndexNumber.clear();
						paymentDetailsPaymentName.clear();
						paymentDetailsGradeChoice.setValue(null);
						paymentDetailsPaymentDate.setValue(null);
						paymentDetailsAmout.clear();
						paymentDetailsPaymentSpecialNote.clear();
					}

				} else {
					showAlert(Alert.AlertType.ERROR, "Error", "Update Failed");
				}

			} else {
				showAlert(Alert.AlertType.ERROR, "Error", "Student number is invalid or not registered to the system");
				achievementsIndexNo.clear();
			}

		} else if (mouseEvent.getSource() == viewPayments) {
			registerStudents.setVisible(false);
			registerTeachers.setVisible(false);
			searchStudent.setVisible(false);
			searchTeacher.setVisible(false);
			editStudent.setVisible(false);
			editTeacher.setVisible(false);
			createClassA.setVisible(false);
			createSubjectA.setVisible(false);
			scheduleExamA.setVisible(false);
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(false);
			addPaymentsA.setVisible(false);
			viewPaymentsA.setVisible(true);
			addAchievementsA.setVisible(false);
			addSport.setVisible(false);
			leavingFormA.setVisible(false);
			dashBoard.setVisible(false);

		}else if(mouseEvent.getSource() == viewPaymentSearchBtn){
			// get search index number
			String indexNumber = viewPaymentIndex.getText();
			// check if index number is empty
			if(indexNumber.isEmpty()){
				showAlert(Alert.AlertType.ERROR, "Error", "Please enter an index number");
			}else{
				// check student is available in the database
				String sql = "SELECT * FROM students_details WHERE index_number = ?";
				rst = executeQuery(sql, indexNumber);
				if(rst.next()){
					// get all payments for the student
					String sql1 = "SELECT * FROM payment_details WHERE index_number = ?";
					rst1 = executeQuery(sql1, indexNumber);
					viewPaymentList.clear();
					// if there are payments
					while(rst1.next()){
						viewPaymentList.add(new PaymentRecord(rst1.getInt("invoice_number"), rst1.getString("payment_name"), rst1.getString("payment_date"), rst1.getInt("payment_amount")));
						viewPaymentTable.setItems(viewPaymentList);
					}
					// set table columns
					viewPaymentInvoice.setCellValueFactory(new PropertyValueFactory<PaymentRecord, Integer>("invoice"));
					viewPaymentName.setCellValueFactory(new PropertyValueFactory<PaymentRecord, String>("name"));
					viewPaymentPaymentDate.setCellValueFactory(new PropertyValueFactory<PaymentRecord, String>("date"));
					viewPaymentPaymentAmount.setCellValueFactory(new PropertyValueFactory<PaymentRecord, Integer>("amount"));
				}else{
					showAlert(Alert.AlertType.ERROR, "Error", "Student not found");
				}
					
			}
		}
	}

	// method to handle all invents in acheivement
	public void handleAchievments(Event mouseEvent) throws SQLException {
		if (mouseEvent.getSource() == addAchievements || mouseEvent.getSource() == addAchievementsB) {
			registerStudents.setVisible(false);
			registerTeachers.setVisible(false);
			searchStudent.setVisible(false);
			searchTeacher.setVisible(false);
			editStudent.setVisible(false);
			editTeacher.setVisible(false);
			createClassA.setVisible(false);
			createSubjectA.setVisible(false);
			scheduleExamA.setVisible(false);
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(false);
			addPaymentsA.setVisible(false);
			viewPaymentsA.setVisible(false);
			addAchievementsA.setVisible(true);
			addSport.setVisible(false);
			leavingFormA.setVisible(false);
			dashBoard.setVisible(false);

			achievementsIndexNo.clear();
			achievementsDate.setValue(null);
			acheivementType.setValue(null);
			achievementsEventName.clear();
			achievementAchievement.clear();

			sportList.clear();
			String sql6 = "SELECT sport_name FROM sport_details";
			rst1 = executeQuery(sql6);
			while (rst1.next()) {
				sportList.add(rst1.getString("sport_name"));
			}
			acheivementType.getItems().addAll(sportList);

		} else if (mouseEvent.getSource() == addAchievementsApplyButton) {
			String stIndex = achievementsIndexNo.getText();
			LocalDate date = achievementsDate.getValue();
			String sport = acheivementType.getValue();
			String name = achievementsEventName.getText();
			String achievement = achievementAchievement.getText();

			if (stIndex.isEmpty() || date == null || sport.isEmpty() || name.isEmpty() || achievement.isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Error", "Fill the all feilds");
			} else {
				String sql5 = "SELECT * FROM students_details WHERE index_number = ?";
				rst2 = executeQuery(sql5, stIndex);
				if (rst2.next()) {
					String sql3 = "SELECT achievement_date FROM achievements_details WHERE index_number = ? AND sport_type = ? AND event_name = ? AND achievements = ?";
					rst = executeQuery(sql3, stIndex, sport, name, achievement);
					if (!rst.next()) {
						String sql4 = "INSERT INTO achievements_details(index_number,achievement_date,sport_type,event_name,achievements)VALUES (?,?,?,?,?)";
						int k = executeUpdate(sql4, stIndex, Date.valueOf(date), sport, name, achievement);
						if (k > 0) {
							showAlert(Alert.AlertType.INFORMATION, "Successfull", "Achievements Updated");
							achievementsIndexNo.clear();
							achievementsDate.setValue(null);
							acheivementType.setValue(null);
							achievementsEventName.clear();
							achievementAchievement.clear();
						} else {
							showAlert(Alert.AlertType.ERROR, "Error", "updateFailed");
						}
					} else {
						showAlert(Alert.AlertType.ERROR, "Error", "This Achievement is updated Already");
						achievementsIndexNo.clear();
						achievementsDate.setValue(null);
						acheivementType.setValue(null);
						achievementsEventName.clear();
						achievementAchievement.clear();
					}
				} else {
					showAlert(Alert.AlertType.ERROR, "Error",
							"Student number is invalid or not registered to the system");
					achievementsIndexNo.clear();
				}

			}

		} else if (mouseEvent.getSource() == addSportsB) {
			registerStudents.setVisible(false);
			registerTeachers.setVisible(false);
			searchStudent.setVisible(false);
			searchTeacher.setVisible(false);
			editStudent.setVisible(false);
			editTeacher.setVisible(false);
			createClassA.setVisible(false);
			createSubjectA.setVisible(false);
			scheduleExamA.setVisible(false);
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(false);
			addPaymentsA.setVisible(false);
			viewPaymentsA.setVisible(false);
			addAchievementsA.setVisible(false);
			addSport.setVisible(true);
			leavingFormA.setVisible(false);
			dashBoard.setVisible(false);

			regsportsName.clear();
			regcoachName.clear();
			

		} else if (mouseEvent.getSource() == registerSportApplyButton) {
			String sportName = (regsportsName.getText()).toUpperCase();
			String coachName = regcoachName.getText();
		
			if (coachName.isEmpty() || sportName.isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Error", "Fill the all feilds");
			} else {
				String sql = "SELECT * FROM sport_details WHERE sport_name = ?";
				rst = executeQuery(sql, sportName);
				if (rst.next()) {
					showAlert(Alert.AlertType.ERROR, "Error", "This sport is already registered ");
					regsportsName.clear();
					regcoachName.clear();
					
				} else {
					String sql2 = "INSERT INTO sport_details(sport_Name,sport_coach)VALUES(?,?)";
					int k = executeUpdate(sql2, sportName, coachName);
					if (k > 0) {
						showAlert(Alert.AlertType.INFORMATION, "Succesfuly updated", "Registration is done");
						regsportsName.clear();
						regcoachName.clear();
					} else {
						showAlert(Alert.AlertType.ERROR, "Error", "Registration Failed something went Wrong ");
					}
				}
			}
		}
	}
	Student tempStd = new Student("","","","");
	// method to handle all invents in leaving
	public void handleLeaving(Event mouseEvent) throws SQLException {

		if (mouseEvent.getSource() == leavingForm) {
			registerStudents.setVisible(false);
			registerTeachers.setVisible(false);
			searchStudent.setVisible(false);
			searchTeacher.setVisible(false);
			editStudent.setVisible(false);
			editTeacher.setVisible(false);
			createClassA.setVisible(false);
			createSubjectA.setVisible(false);
			scheduleExamA.setVisible(false);
			uploadResultA.setVisible(false);
			viewStudentPerformance.setVisible(false);
			viewTeacherPerformance.setVisible(false);
			viewClassPerformance.setVisible(false);
			addNoticesA.setVisible(false);
			viewNoticesA.setVisible(false);
			addPaymentsA.setVisible(false);
			viewPaymentsA.setVisible(false);
			addAchievementsA.setVisible(false);
			addSport.setVisible(false);
			leavingFormA.setVisible(true);
			dashBoard.setVisible(false);
			leavingFormSearchTextField.clear();
			;
			leavingFormIndexNumber.setText("Details");
			leavingFormFullName.setText("Details");
			leavingFormGender.setText("Details");
			leavingFormContactNumber.setText("Details");
			leavingFormDateOfBirth.setText("Details");
			leavingFormRegistrationDate.setText("Details");
			leavingFormNationality.setText("Details");
			leavingFormReligion.setText("Details");
			leavingFormGuardianName.setText("Details");
			leavingFormGuardianAddress.setText("Details");
			leavingFormRegisteredGrade.setText("Details");
			leavingFormSchoolAttendedBefore.setText("Details");
			leavingFormAcheivement1.setText("Event Type:-");
			leavingFormAcheivement2.setText("Event Type:-");
			leavingFormAcheivement3.setText("Event Type:-");
			leavingFormAcheivement4.setText("Event Type:-");
			leavingFormAcheivement1Details.setText("Details");
			leavingFormAcheivement2Details.setText("Details");
			leavingFormAcheivement3Details.setText("Details");
			leavingFormAcheivement4Details.setText("Details");
			leavingFormSchoolLeavingDate.setText("Details");
			leavingFormSchoolTimePeriod.setText("Details");
		} else if (mouseEvent.getSource() == leavingFormSearchBtn) {
			String indexNumber = leavingFormSearchTextField.getText();
			if (indexNumber.isEmpty()) {
				showAlert(Alert.AlertType.ERROR, "Error", "Please Enter IndexNumber");
			} else {
				String dob = "";
				String sql = "SELECT * FROM students_details WHERE index_number = ?";
				rst = executeQuery(sql, indexNumber);
				if (rst.next()) {
					leavingFormIndexNumber.setText(rst.getString("index_number"));
					tempStd.setNumber(rst.getString("index_number"));
					tempStd.resetActivities();
					
					leavingFormFullName.setText(rst.getString("full_name"));
					tempStd.setName(rst.getString("full_name"));
					
					leavingFormGender.setText(rst.getString("gender"));
					
					leavingFormDateOfBirth.setText(rst.getDate("dateOfBirth").toString());
					dob = rst.getDate("dateOfBirth").toString();
					
					leavingFormNationality.setText(rst.getString("nationality"));
					
					leavingFormReligion.setText(rst.getString("religion"));
					
					leavingFormContactNumber.setText(rst.getString("contactDetails"));
					
					leavingFormGuardianName.setText(rst.getString("guardianName"));
					
					leavingFormGuardianAddress.setText(rst.getString("guardianAddress"));
					
					leavingFormRegistrationDate.setText(rst.getDate("registrationDate").toString());
					tempStd.setRegYear(rst.getDate("registrationDate").toString());
					
					leavingFormRegisteredGrade.setText(rst.getString("registeredGrade"));
					tempStd.setRegGrade(rst.getString("registeredGrade"));
					
					leavingFormSchoolAttendedBefore.setText(rst.getString("beforeschoolName"));
					
					String sql2 = "SELECT * FROM achievements_details WHERE index_number = ?";
					rst2 = executeQuery(sql2, indexNumber);
					if (rst2.next()) {
						
						String sport = rst2.getString("sport_type");
						String date = rst2.getDate("achievement_date").toString(); // Correct column name
						String event = rst2.getString("event_name");
						String achievement = rst2.getString("achievements");
						// Set text for the labels
						leavingFormAcheivement1.setText(sport);
						leavingFormAcheivement1Details.setText(
								"Date: " + date + ", Event name: " + event + ", Achievements: " + achievement);
						tempStd.setActivities("Date: " + date + ", Event name: "+sport+"-"+ event + ", Achievements: " + achievement);
						if (rst2.next()) {
							
							sport = rst2.getString("sport_type");
							date = rst2.getDate("achievement_date").toString(); // Correct column name
							event = rst2.getString("event_name");
							achievement = rst2.getString("achievements");

							// Set text for the labels
							leavingFormAcheivement2.setText(sport);
							leavingFormAcheivement2Details.setText(
									"Date: " + date + ", Event name: " + event + ", Achievements: " + achievement);
							tempStd.setActivities("Date: " + date + ", Event name: "+sport+"-"+ event + ", Achievements: " + achievement);
						}else if (rst2.next()) {
							
							sport = rst2.getString("sport_type");
							date = rst2.getDate("achievement_date").toString(); // Correct column name
							event = rst2.getString("event_name");
							achievement = rst2.getString("achievements");
							System.out.println(sport+' '+date+' '+event+' '+achievement);

							// Set text for the labels
							leavingFormAcheivement3.setText(sport);
							leavingFormAcheivement3Details.setText(
									"Date: " + date + ", Event name: " + event + ", Achievements: " + achievement);
							tempStd.setActivities("Date: " + date + ", Event name: "+sport+"-"+ event + ", Achievements: " + achievement);
						}else if (rst2.next()) {
							
							sport = rst2.getString("sport_type");
							date = rst2.getDate("achievement_date").toString(); // Correct column name
							event = rst2.getString("event_name");
							achievement = rst2.getString("achievements");
							System.out.println(sport+' '+date+' '+event+' '+achievement);

							// Set text for the labels
							leavingFormAcheivement4.setText(sport);
							leavingFormAcheivement4Details.setText(
									"Date: " + date + ", Event name: " + event + ", Achievements: " + achievement);
							tempStd.setActivities("Date: " + date + ", Event name: "+sport+"-"+ event + ", Achievements: " + achievement);
							
						}
					} else {
						leavingFormAcheivement1.setText("none");
						leavingFormAcheivement2.setText("none");
						leavingFormAcheivement3.setText("none");
						leavingFormAcheivement4.setText("none");
						leavingFormAcheivement1Details.setText("none");
						leavingFormAcheivement2Details.setText("none");
						leavingFormAcheivement3Details.setText("none");
						leavingFormAcheivement4Details.setText("none");
					}
					leavingFormSchoolLeavingDate.setText(LocalDate.now().toString());
					int dif = Integer.parseInt(LocalDate.now().toString().substring(0,4))- Integer.parseInt(dob.substring(0,4));
					leavingFormSchoolTimePeriod.setText(String.valueOf(dif));
					System.out.println("1"+tempStd.getName()+" "+ tempStd.getRegYear()+" "+tempStd.getRegGrade()+" "+tempStd.getActivites());
					
				} else {
					showAlert(Alert.AlertType.ERROR, "Error", "Student not found");
				}
			}

		} else if (mouseEvent.getSource() == leavingFormPrintBtn) {

			String stdName = tempStd.getName();
			String rgYear = tempStd.getRegYear();
			String nowGrade = tempStd.getRegGrade();
			String[] activities = tempStd.getActivites();
//			String[] activities = new String[4];
			System.out.println(tempStd.getName()+" "+ tempStd.getRegYear()+" "+tempStd.getRegGrade()+" "+tempStd.getActivites());
			
			
	        String dest = "LeavingCertificateOf"+tempStd.getNumber()+".pdf";
	        Document document = new Document();
	        
	        try {
//	            PdfWriter.getInstance(document, new FileOutputStream(dest));
//	            document.open();
	        	PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));

	            // Define a rectangle for the border
	            Rectangle pageSize = new Rectangle(PageSize.A4);
	            pageSize.setBorder(Rectangle.BOX);
	            pageSize.setBorderWidth(10);
	            pageSize.setBorderColor(BaseColor.BLACK);

	            // Set the rectangle as the page size
	            document.setPageSize(pageSize);
	            document.open();
	         // Add header
	            Font headerFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 16, BaseColor.BLACK);
	            Paragraph header = new Paragraph("Kuli/ Sri Rewatha Rathanapala College\nStudent Leaving Certificate", headerFont);
	            header.setAlignment(Element.ALIGN_CENTER);
	            document.add(header);
	            
	         // Add logo
	            Image logo = Image.getInstance("logo.png");
	            logo.setAlignment(Element.ALIGN_CENTER);
	            logo.scaleToFit(100, 100); // Adjust the size of the logo
	            document.add(logo);
	            
	            // Add some space after the logo
	            document.add(Chunk.NEWLINE);
	            
	            Font font = FontFactory.getFont(FontFactory.TIMES, 12, BaseColor.BLACK);
	            
	            Paragraph heading = new Paragraph("To whom it may concern,", font);
	            heading.setAlignment(Element.ALIGN_LEFT);
	            document.add(heading);
	            
	            Paragraph body = new Paragraph(
	                "This is to certify that Ms./Master "+stdName+ "has been a student of this school from " +
	                rgYear+" to "+LocalDate.now().getYear()+". She/ he was educated up to grade "+nowGrade+" in year "+LocalDate.now().getYear()+" .", font);
	            body.setSpacingBefore(20);
	            body.setSpacingAfter(20);
	            document.add(body);
	            
	            Paragraph activitiesIntro = new Paragraph(
	                "In addition to the curriculars, the student actively participated in the following " +
	                "extracurricular and co-curricular activities:", font);
	            activitiesIntro.setSpacingAfter(10);
	            document.add(activitiesIntro);
	            
	            // Adding activity placeholders
	            for (int i = 1; i <= 4; i++) {
	                Paragraph activity = new Paragraph(String.valueOf(i)+"."+activities[i-1], font);
	                activity.setSpacingAfter(5);
	                document.add(activity);
	            }
	            
	            Paragraph signature = new Paragraph(
	                "………………… …………………………\nDate Principal", font);
	            signature.setSpacingBefore(40);
	            document.add(signature);
	            
	            document.close();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

		}

	}
	@FXML
	public void removeClassStudent(ActionEvent event) throws SQLException {
		int selectedId = tempStudentEnroll.getSelectionModel().getSelectedIndex();
	    
	    // Check if a row is selected
	    if (selectedId >= 0) {
	        // Print the invoice of the selected item
	        System.out.println(tempStudentEnroll.getSelectionModel().getSelectedItems().get(0).getNo());
	        String clasID = tempStudentEnroll.getSelectionModel().getSelectedItems().get(0).getNo();
	        String indexId = tempStudentEnroll.getSelectionModel().getSelectedItems().get(0).getNumber();
	        
	        // Remove the selected item from the table
	        tempStudentEnroll.getItems().remove(selectedId);
	        deletestudentFromDatabase(clasID, indexId);
	    } else {
	        // Handle the case where no row is selected
	        System.out.println("No row selected.");
	    }
	}
	
	private void deletestudentFromDatabase(String clasID,String indexId) throws SQLException {
	     
        String sql = "DELETE FROM class_studentdetails WHERE index_number = ? and classID = ?"; // Update with your table and column names

        
        PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.setString(1, indexId);
        pstmt.setString(2, clasID);
        
        int affectedRows = pstmt.executeUpdate();
        
        if (affectedRows > 0) {
            System.out.println("Invoice deleted successfully.");
        } else {
            System.out.println("No invoice found with the given invoice number.");
        }
      
    }
	
	@FXML
	public void removesubjectstd(ActionEvent event) throws SQLException {
		int selectedId = tempStudentSubjecEnroll.getSelectionModel().getSelectedIndex();
	    
	    // Check if a row is selected
	    if (selectedId >= 0) {
	        // Print the invoice of the selected item
	        System.out.println(tempStudentSubjecEnroll.getSelectionModel().getSelectedItems().get(0).getNo());
	        String indexId = tempStudentSubjecEnroll.getSelectionModel().getSelectedItems().get(0).getNumber();
	        
	        // Remove the selected item from the table
	        tempStudentSubjecEnroll.getItems().remove(selectedId);
	        deleteclassstudentFromDatabase(indexId);
	    } else {
	        // Handle the case where no row is selected
	        System.out.println("No row selected.");
	    }
	} 
	private void deleteclassstudentFromDatabase(String indexId) throws SQLException {
	     
        String sql = "DELETE FROM subject_student WHERE student_id = ? "; // Update with your table and column names

        
        PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.setString(1, indexId);
        
        int affectedRows = pstmt.executeUpdate();
        
        if (affectedRows > 0) {
            System.out.println("Invoice deleted successfully.");
        } else {
            System.out.println("No invoice found with the given invoice number.");
        }
      
    }
	

	@FXML
	public void searchinfo(ActionEvent event) throws SQLException {
		String year=createSubjectYearChoice.getValue();
		String grade=createSubjectGradeChoice.getValue();
		String subject=createSubjectSubject1Choice.getValue();
		String clas=createSubjectClassChoice.getValue();
		
		String sql2 = "SELECT subject_id FROM subject_details WHERE subject_name =? and grade= ? ";
		
		rst1 = executeQuery(sql2, subject, grade);
		String subj = null;
		if(rst1.next()) {
			subj = String.valueOf(rst1.getInt("subject_id"));
		}
		
		String sql = "SELECT sub_teach_id, teacher_id FROM subject_teacherdetails WHERE year = ? and grade = ? and subject_id = ? and class = ?";
		rst = executeQuery(sql, year, grade,subj, clas);
		
		if(rst.next()) {
			String subTeaId = rst.getString("sub_teach_id");
			String teaId = rst.getString("teacher_id");
			createSubjectTeacherNO.setText(teaId);
			studentSubjectList.clear();
			
			sql = "SELECT student_id FROM subject_student WHERE sub_teach_id = ?";
			rst1 = executeQuery(sql,subTeaId);
			while (rst1.next()) {
				String studID = rst1.getString("student_id");
				sql = "SELECT full_name FROM students_details WHERE index_number = ?";
				rst2 = executeQuery(sql,studID);
				
				String name = null;
				
				if(rst2.next()) {
					name = rst2.getString("full_name");
				}
				
				studentSubjectList.add(new StudentSubject(subTeaId, studID,name));
				tempStudentSubjecEnroll.setItems(studentSubjectList);
			}
			// set table columns
			colsNo.setCellValueFactory(new PropertyValueFactory<StudentSubject, String>("no"));
			colsIndex_Number.setCellValueFactory(new PropertyValueFactory<StudentSubject, String>("number"));
			colsName.setCellValueFactory(new PropertyValueFactory<StudentSubject, String>("name"));
			
			
		}
		
	}
	
	@FXML
	public void findClass(ActionEvent event) throws SQLException {
		studentList.clear();
		String year = createClassYearChoice.getValue();
		String grade = createClassGradeChoice.getValue();
		String classs = createClassClassChoice.getValue();
		System.out.println(year+" "+grade+" "+classs);
		if (year.isEmpty()) {
			showAlert(Alert.AlertType.ERROR, "Error", "Student index Feild is empty");
		} else {
			String sql = "SELECT classID FROM class_details WHERE year = ?	and grade = ? and class = ?";
			rst = executeQuery(sql, year, grade, classs);
			String clasID = null;
			if (rst.next()) {
				clasID = rst.getString("classID");
				
				String sql1 = "SELECT * FROM `students_details` WHERE index_number in (SELECT index_number FROM class_studentdetails WHERE classID = ?)";
				rst1 = executeQuery(sql1, clasID);
//				colNo;colIndex_Number;colName;studentList
				while(rst1.next()) {
					studentList.add(new PersonRecord(clasID, rst1.getString("index_number"),rst1.getString("full_name")));
					
				}
				tempStudentEnroll.setItems(studentList);
				if(studentList.isEmpty()) {
					showAlert(Alert.AlertType.ERROR, "Error", "There are no Students");
					
				}
				colNo.setCellValueFactory(new PropertyValueFactory<PersonRecord, String>("no"));
				colIndex_Number.setCellValueFactory(new PropertyValueFactory<PersonRecord, String>("number"));
				colName.setCellValueFactory(new PropertyValueFactory<PersonRecord, String>("name"));
				
				
			}else {
				showAlert(Alert.AlertType.ERROR, "Error", "There are no Students and a teacher.");
				return;
			}
			sql = "SELECT registration_number FROM class_teacher WHERE classID = ?";
			rst = executeQuery(sql, clasID);
			if(rst.next()) {
				String teachID =rst.getString("registration_number");
				
				ClassTeacher.setText(teachID);
				
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
	public void deleteInvoice(ActionEvent event) throws SQLException {
//		int selectedId = viewPaymentTable.getSelectionModel().getSelectedIndex();
//		System.out.println(viewPaymentTable.getSelectionModel().getSelectedItems().get(selectedId).getInvoice());
//		viewPaymentTable.getItems().remove(selectedId);
//		
		int selectedId = viewPaymentTable.getSelectionModel().getSelectedIndex();
	    
	    // Check if a row is selected
	    if (selectedId >= 0) {
	        // Print the invoice of the selected item
	        System.out.println(viewPaymentTable.getSelectionModel().getSelectedItems().get(0).getInvoice());
	        int invoiceId = viewPaymentTable.getSelectionModel().getSelectedItems().get(0).getInvoice();
	        
	        // Remove the selected item from the table
	        viewPaymentTable.getItems().remove(selectedId);
	        System.out.println(String.valueOf(invoiceId));
	        deleteInvoiceFromDatabase(String.valueOf(invoiceId));
	    } else {
	        // Handle the case where no row is selected
	        System.out.println("No row selected.");
	    }
	    
		
	}
//	@FXML
//	public void removeResult(ActionEvent event) throws SQLException {
//
//		int selectedId = resultTempDetails.getSelectionModel().getSelectedIndex();
//	    
//	    // Check if a row is selected
//	    if (selectedId >= 0) {
//	        // Print the invoice of the selected item
//	        String no = resultTempDetails.getSelectionModel().getSelectedItems().get(0).getNo();
//	        String mark = resultTempDetails.getSelectionModel().getSelectedItems().get(0).getMarks();
//	        
//	        // Remove the selected item from the table
//	        resultTempDetails.getItems().remove(selectedId);
//	        deleteresFromDatabase(no, mark);
//	    } else {
//	        // Handle the case where no row is selected
//	        System.out.println("No row selected.");
//	    }
//	    
//		
//	}
//	private void deleteresFromDatabase(String no, String mark) throws SQLException {
//		String subj = resultSubject.getValue();
//		String grade = resultGrade.getValue();
//		String clas = resultClass.getValue();
//		String year = resultYear.getValue();
//		String term = resultTerm.getValue();
//		
//		String sql2 = "SELECT subject_id FROM subject_details WHERE subject_name =? and grade= ? ";
//		
//		rst1 = executeQuery(sql2, subj, grade);
//		String subid = null;
//		if(rst1.next()) {
//			subid = String.valueOf(rst1.getInt("subject_id"));
//		}
//		
//		
//        String sql = "DELETE FROM result_details WHERE subj_id=? and	grade=? and	year=? and	term=? and	marks=? and	student_id=? and class=?"; // Update with your table and column names
//
//        
//        PreparedStatement pstmt = con.prepareStatement(sql);
//
//        pstmt.setString(1, subid);
//        pstmt.setString(2, grade);
//        pstmt.setString(3, year);
//        pstmt.setString(4, term);
//        pstmt.setString(5, mark);
//        pstmt.setString(6, no);
//        pstmt.setString(7, clas);
//        int affectedRows = pstmt.executeUpdate();
//        
//        if (affectedRows > 0) {
//            System.out.println("Invoice deleted successfully.");
//        } else {
//            System.out.println("No invoice found with the given invoice number.");
//        }
//      
//    }
//	
	@FXML
	public void  hide_manual(ActionEvent event) {
		stdName.setVisible(false);
		createClassStudentNO.setVisible(false);
		classStudExcel.setVisible(true);
	}
	
		
	@FXML
	public void  hide_upload(ActionEvent event) {
		stdName.setVisible(true);
		createClassStudentNO.setVisible(true);
		classStudExcel.setVisible(false);
	}
	@FXML
	public void  hideAll(ActionEvent event) {
		stdName.setVisible(false);
		createClassStudentNO.setVisible(false);
		classStudExcel.setVisible(false);
	}

	@FXML
	public void  uploadOpt3(ActionEvent event) {
		
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Open File Dialog");

		Stage stage = (Stage)createSubjectA.getScene().getWindow();
		
		File file = filechooser.showOpenDialog(stage);
		String grade = createSubjectGradeChoice.getValue();
		
		try (FileInputStream fis = new FileInputStream(file);
		         XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
		        
		        XSSFSheet sheet =  workbook.getSheetAt(0);
		        int numOfColumns = sheet.getRow(0).getLastCellNum();

	            for (int colIndex = 0; colIndex < numOfColumns; colIndex++) {
	                int rowId = 0;
	                String optID = null;
	                Boolean update = false;  
	            	for (Row row : sheet) {
	            		rowId++;
	            		
	                    Cell cell = row.getCell(colIndex);
	                    
	                    if (cell != null) {
	                        switch (cell.getCellType()) {
	                            case NUMERIC:
	                            	if(rowId == 2) {
	                            		if(!update) {
	                            			String sql = "INSERT INTO optional_subject_teacher(opt_subject_id,	registration_number	) values(?,?)";
	                            			int k = executeUpdate(sql, optID, String.valueOf((int) cell.getNumericCellValue()));
	                            			
	                            			String sql1 = "INSERT INTO class_subject_teacherdetails(classID,	registration_number	) values(?,?)";
	                            			String sql2 = "select classID from class_details where grade = ? and year = ? and class = ?";
	                            			rst1 = executeQuery(sql2,  createSubjectGradeChoice.getValue(),  createSubjectYearChoice.getValue(), createSubjectClassChoice.getValue());
	                                		String classID = null;
	                                		if(rst1.next()) {
	                                			classID = rst1.getString("classID");
	                                		}
	                             
	                            			k = executeUpdate(sql1, classID, String.valueOf((int) cell.getNumericCellValue()));
	                            			
	                            		}else {
	                            			
	                            			String sql2 = "select classID from class_details where grade = ? and year = ? and class = ?";
	                            			rst1 = executeQuery(sql2,  createSubjectGradeChoice.getValue(),  createSubjectYearChoice.getValue(), createSubjectClassChoice.getValue());
	                                		String classID = null;
	                                		if(rst1.next()) {
	                                			classID = rst1.getString("classID");
	                                		}
	                                		
	                            			String sql = "Update class_subject_teacherdetails set registration_number = ? where classID = ?";
		                            		
		                            		
		                            		int k = executeUpdate(sql, String.valueOf((int) cell.getNumericCellValue()), classID);
	                            		}
	                            		
	                            	}else {
	                            		if(!update) {
	                            			String sql = "INSERT INTO optional_subject_student(opt_subject_id,	index_number) values(?,?)";
		                            		
		                            		
		                            		int k = executeUpdate(sql, optID,  String.valueOf((int) cell.getNumericCellValue()));
	                            		}else {
	                            			String sql = "Delete from optional_subject_student where index_number in (select index_number from class_studentdetails where classID = (select classID from class_details where grade = ? and year = ? and class = ?))";
		                            		int k = executeUpdate(sql, createSubjectGradeChoice.getValue(),  createSubjectYearChoice.getValue(), createSubjectClassChoice.getValue());
		                            		
	                            			sql = "INSERT INTO optional_subject_student(opt_subject_id,	index_number) values(?,?)";
		                            		k = executeUpdate(sql, optID,  String.valueOf((int) cell.getNumericCellValue()));
	                            		}
	                            		
	                            	}
	                            	
//	                                indexes3.add(String.valueOf((int) cell.getNumericCellValue()));
//	                                System.out.print(String.valueOf((int) cell.getNumericCellValue()) + "\t");
	                                break;
	                                
	                                
	                            case STRING:
	                            	String sql4 = "Select * from optional_subject_details where opt_subject_name = ?";
	                            	rst1 = executeQuery(sql4, cell.getStringCellValue().toUpperCase());
                            		
                            		if(!rst1.next()) {
                            			
                            			String sql = "INSERT INTO optional_subject_details(subject_id,	opt_subject_name) values(?,?)";
	                            		
	                            		String sql2 = "SELECT subject_id FROM subject_details WHERE subject_name =? and grade= ? ";
	                            		
	                            		rst1 = executeQuery(sql2, "Optional Subject3", grade);
	                            		String subid = null;
	                            		if(rst1.next()) {
	                            			subid = String.valueOf(rst1.getInt("subject_id"));
	                            		}
	                            		
	                            		
	                            		int k = executeUpdate(sql, subid, cell.getStringCellValue().toUpperCase());
	                            		
	                            		sql2 = "SELECT opt_subject_id FROM optional_subject_details WHERE opt_subject_name =?";
	                            		rst1 = executeQuery(sql2,  cell.getStringCellValue().toUpperCase());
	                            		if(rst1.next()) {
	                            			optID =  String.valueOf(rst1.getInt("opt_subject_id"));
	                            		}
	                            		
                            		}else {
                            			update = true;
                            		}
	                            	 
	                                // Uncomment if you want to handle string cells
	                                // System.out.print(cell.getStringCellValue() + "\t");
	                                break;
	                            default:
	                                break;
	                        }
	                    }
	                }
	                
	            }
		        
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		 	catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
	@FXML
	public void  uploadOpt2(ActionEvent event) {
			
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Open File Dialog");

		Stage stage = (Stage)createSubjectA.getScene().getWindow();
		
		File file = filechooser.showOpenDialog(stage);
		String grade = createSubjectGradeChoice.getValue();
		
		try (FileInputStream fis = new FileInputStream(file);
		         XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
		        
		        XSSFSheet sheet =  workbook.getSheetAt(0);
		        int numOfColumns = sheet.getRow(0).getLastCellNum();

	            for (int colIndex = 0; colIndex < numOfColumns; colIndex++) {
	                int rowId = 0;
	                String optID = null;
	                Boolean update = false;  
	            	for (Row row : sheet) {
	            		rowId++;
	            		
	                    Cell cell = row.getCell(colIndex);
	                    
	                    if (cell != null) {
	                        switch (cell.getCellType()) {
	                            case NUMERIC:
	                            	if(rowId == 2) {
	                            		if(!update) {
	                            			String sql = "INSERT INTO optional_subject_teacher(opt_subject_id,	registration_number	) values(?,?)";
	                            			int k = executeUpdate(sql, optID, String.valueOf((int) cell.getNumericCellValue()));
	                            			
	                            			String sql1 = "INSERT INTO class_subject_teacherdetails(classID,	registration_number	) values(?,?)";
	                            			String sql2 = "select classID from class_details where grade = ? and year = ? and class = ?";
	                            			rst1 = executeQuery(sql2,  createSubjectGradeChoice.getValue(),  createSubjectYearChoice.getValue(), createSubjectClassChoice.getValue());
	                                		String classID = null;
	                                		if(rst1.next()) {
	                                			classID = rst1.getString("classID");
	                                		}
	                             
	                            			k = executeUpdate(sql1, classID, String.valueOf((int) cell.getNumericCellValue()));
	                            			
	                            		}else {
	                            			
	                            			String sql2 = "select classID from class_details where grade = ? and year = ? and class = ?";
	                            			rst1 = executeQuery(sql2,  createSubjectGradeChoice.getValue(),  createSubjectYearChoice.getValue(), createSubjectClassChoice.getValue());
	                                		String classID = null;
	                                		if(rst1.next()) {
	                                			classID = rst1.getString("classID");
	                                		}
	                                		
	                            			String sql = "Update class_subject_teacherdetails set registration_number = ? where classID = ?";
		                            		
		                            		
		                            		int k = executeUpdate(sql, String.valueOf((int) cell.getNumericCellValue()), classID);
	                            		}
	                            		
	                            	}else {
	                            		if(!update) {
	                            			String sql = "INSERT INTO optional_subject_student(opt_subject_id,	index_number) values(?,?)";
		                            		
		                            		
		                            		int k = executeUpdate(sql, optID,  String.valueOf((int) cell.getNumericCellValue()));
	                            		}else {
	                            			String sql = "Delete from optional_subject_student where index_number in (select index_number from class_studentdetails where classID = (select classID from class_details where grade = ? and year = ? and class = ?))";
		                            		int k = executeUpdate(sql, createSubjectGradeChoice.getValue(),  createSubjectYearChoice.getValue(), createSubjectClassChoice.getValue());
		                            		
	                            			sql = "INSERT INTO optional_subject_student(opt_subject_id,	index_number) values(?,?)";
		                            		k = executeUpdate(sql, optID,  String.valueOf((int) cell.getNumericCellValue()));
	                            		}
	                            		
	                            	}
	                            	
//	                                indexes3.add(String.valueOf((int) cell.getNumericCellValue()));
//	                                System.out.print(String.valueOf((int) cell.getNumericCellValue()) + "\t");
	                                break;
	                                
	                                
	                            case STRING:
	                            	String sql4 = "Select * from optional_subject_details where opt_subject_name = ?";
	                            	rst1 = executeQuery(sql4, cell.getStringCellValue().toUpperCase());
                            		
                            		if(!rst1.next()) {
                            			
                            			String sql = "INSERT INTO optional_subject_details(subject_id,	opt_subject_name) values(?,?)";
	                            		
	                            		String sql2 = "SELECT subject_id FROM subject_details WHERE subject_name =? and grade= ? ";
	                            		
	                            		rst1 = executeQuery(sql2, "Optional Subject2", grade);
	                            		String subid = null;
	                            		if(rst1.next()) {
	                            			subid = String.valueOf(rst1.getInt("subject_id"));
	                            		}
	                            		
	                            		
	                            		int k = executeUpdate(sql, subid, cell.getStringCellValue().toUpperCase());
	                            		
	                            		sql2 = "SELECT opt_subject_id FROM optional_subject_details WHERE opt_subject_name =?";
	                            		rst1 = executeQuery(sql2,  cell.getStringCellValue().toUpperCase());
	                            		if(rst1.next()) {
	                            			optID =  String.valueOf(rst1.getInt("opt_subject_id"));
	                            		}
	                            		
                            		}else {
                            			update = true;
                            		}
	                            	 
	                                // Uncomment if you want to handle string cells
	                                // System.out.print(cell.getStringCellValue() + "\t");
	                                break;
	                            default:
	                                break;
	                        }
	                    }
	                }
	                
	            }
		        
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		 	catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
	@FXML
	public void  uploadOpt1(ActionEvent event) {
		
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Open File Dialog");

		Stage stage = (Stage)createSubjectA.getScene().getWindow();
		
		File file = filechooser.showOpenDialog(stage);
		String grade = createSubjectGradeChoice.getValue();
		
		try (FileInputStream fis = new FileInputStream(file);
		         XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
		        
		        XSSFSheet sheet =  workbook.getSheetAt(0);
		        int numOfColumns = sheet.getRow(0).getLastCellNum();

	            for (int colIndex = 0; colIndex < numOfColumns; colIndex++) {
	                int rowId = 0;
	                String optID = null;
	                Boolean update = false;  
	            	for (Row row : sheet) {
	            		rowId++;
	            		
	                    Cell cell = row.getCell(colIndex);
	                    
	                    if (cell != null) {
	                        switch (cell.getCellType()) {
	                            case NUMERIC:
	                            	if(rowId == 2) {
	                            		if(!update) {
	                            			String sql = "INSERT INTO optional_subject_teacher(opt_subject_id,	registration_number	) values(?,?)";
	                            			int k = executeUpdate(sql, optID, String.valueOf((int) cell.getNumericCellValue()));
	                            			
	                            			String sql1 = "INSERT INTO class_subject_teacherdetails(classID,	registration_number	) values(?,?)";
	                            			String sql2 = "select classID from class_details where grade = ? and year = ? and class = ?";
	                            			rst1 = executeQuery(sql2,  createSubjectGradeChoice.getValue(),  createSubjectYearChoice.getValue(), createSubjectClassChoice.getValue());
	                                		String classID = null;
	                                		if(rst1.next()) {
	                                			classID = rst1.getString("classID");
	                                		}
	                             
	                            			k = executeUpdate(sql1, classID, String.valueOf((int) cell.getNumericCellValue()));
	                            			
	                            		}else {
	                            			
	                            			String sql2 = "select classID from class_details where grade = ? and year = ? and class = ?";
	                            			rst1 = executeQuery(sql2,  createSubjectGradeChoice.getValue(),  createSubjectYearChoice.getValue(), createSubjectClassChoice.getValue());
	                                		String classID = null;
	                                		if(rst1.next()) {
	                                			classID = rst1.getString("classID");
	                                		}
	                                		
	                            			String sql = "Update class_subject_teacherdetails set registration_number = ? where classID = ?";
		                            		
		                            		
		                            		int k = executeUpdate(sql, String.valueOf((int) cell.getNumericCellValue()), classID);
	                            		}
	                            		
	                            	}else {
	                            		if(!update) {
	                            			String sql = "INSERT INTO optional_subject_student(opt_subject_id,	index_number) values(?,?)";
		                            		
		                            		
		                            		int k = executeUpdate(sql, optID,  String.valueOf((int) cell.getNumericCellValue()));
	                            		}else {
	                            			String sql = "Delete from optional_subject_student where index_number in (select index_number from class_studentdetails where classID = (select classID from class_details where grade = ? and year = ? and class = ?))";
		                            		int k = executeUpdate(sql, createSubjectGradeChoice.getValue(),  createSubjectYearChoice.getValue(), createSubjectClassChoice.getValue());
		                            		
	                            			sql = "INSERT INTO optional_subject_student(opt_subject_id,	index_number) values(?,?)";
		                            		k = executeUpdate(sql, optID,  String.valueOf((int) cell.getNumericCellValue()));
	                            		}
	                            		
	                            	}
	                            	
//	                                indexes3.add(String.valueOf((int) cell.getNumericCellValue()));
//	                                System.out.print(String.valueOf((int) cell.getNumericCellValue()) + "\t");
	                                break;
	                                
	                                
	                            case STRING:
	                            	String sql4 = "Select * from optional_subject_details where opt_subject_name = ?";
	                            	rst1 = executeQuery(sql4, cell.getStringCellValue().toUpperCase());
                            		
                            		if(!rst1.next()) {
                            			
                            			String sql = "INSERT INTO optional_subject_details(subject_id,	opt_subject_name) values(?,?)";
	                            		
	                            		String sql2 = "SELECT subject_id FROM subject_details WHERE subject_name =? and grade= ? ";
	                            		
	                            		rst1 = executeQuery(sql2, "Optional Subject1", grade);
	                            		String subid = null;
	                            		if(rst1.next()) {
	                            			subid = String.valueOf(rst1.getInt("subject_id"));
	                            		}
	                            		
	                            		
	                            		int k = executeUpdate(sql, subid, cell.getStringCellValue().toUpperCase());
	                            		
	                            		sql2 = "SELECT opt_subject_id FROM optional_subject_details WHERE opt_subject_name =?";
	                            		rst1 = executeQuery(sql2,  cell.getStringCellValue().toUpperCase());
	                            		if(rst1.next()) {
	                            			optID =  String.valueOf(rst1.getInt("opt_subject_id"));
	                            		}
	                            		
                            		}else {
                            			update = true;
                            		}
	                            	 
	                                // Uncomment if you want to handle string cells
	                                // System.out.print(cell.getStringCellValue() + "\t");
	                                break;
	                            default:
	                                break;
	                        }
	                    }
	                }
	                
	            }
		        
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		 	catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
	@FXML
	public void  uplo12(ActionEvent event) throws SQLException {
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Open File Dialog");

		Stage stage = (Stage)uploadResultA.getScene().getWindow();
		
		File file = filechooser.showOpenDialog(stage);
		String grade = createSubjectGradeChoice.getValue();
		
		String subjectID = null;
		String examID = null;
		
		String sql2 = "select subject_id from subject_details where grade = ? and subject_name = ? ";
		rst1 = executeQuery(sql2,  resultGrade.getValue(),  examSubject122.getText() );
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
	public void  uplo11(ActionEvent event) throws SQLException {
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Open File Dialog");

		Stage stage = (Stage)uploadResultA.getScene().getWindow();
		
		File file = filechooser.showOpenDialog(stage);
		String grade = createSubjectGradeChoice.getValue();
		
		String subjectID = null;
		String examID = null;
		
		String sql2 = "select subject_id from subject_details where grade = ? and subject_name = ? ";
		rst1 = executeQuery(sql2,  resultGrade.getValue(),  examSubject112.getText() );
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
	public void  uplo10(ActionEvent event) throws SQLException {
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Open File Dialog");

		Stage stage = (Stage)uploadResultA.getScene().getWindow();
		
		File file = filechooser.showOpenDialog(stage);
		String grade = createSubjectGradeChoice.getValue();
		
		String subjectID = null;
		String examID = null;
		
		String sql2 = "select subject_id from subject_details where grade = ? and subject_name = ? ";
		rst1 = executeQuery(sql2,  resultGrade.getValue(),  examSubject102.getText() );
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
	public void  uplo9(ActionEvent event) throws SQLException {
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Open File Dialog");

		Stage stage = (Stage)uploadResultA.getScene().getWindow();
		
		File file = filechooser.showOpenDialog(stage);
		String grade = createSubjectGradeChoice.getValue();
		
		String subjectID = null;
		String examID = null;
		
		String sql2 = "select subject_id from subject_details where grade = ? and subject_name = ? ";
		rst1 = executeQuery(sql2,  resultGrade.getValue(),  examSubject92.getText() );
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
	public void  uplo8(ActionEvent event) throws SQLException {
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Open File Dialog");

		Stage stage = (Stage)uploadResultA.getScene().getWindow();
		
		File file = filechooser.showOpenDialog(stage);
		String grade = createSubjectGradeChoice.getValue();
		
		String subjectID = null;
		String examID = null;
		
		String sql2 = "select subject_id from subject_details where grade = ? and subject_name = ? ";
		rst1 = executeQuery(sql2,  resultGrade.getValue(),  examSubject82.getText() );
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
	public void  uplo7(ActionEvent event) throws SQLException {
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Open File Dialog");

		Stage stage = (Stage)uploadResultA.getScene().getWindow();
		
		File file = filechooser.showOpenDialog(stage);
		String grade = createSubjectGradeChoice.getValue();
		
		String subjectID = null;
		String examID = null;
		
		String sql2 = "select subject_id from subject_details where grade = ? and subject_name = ? ";
		rst1 = executeQuery(sql2,  resultGrade.getValue(),  examSubject72.getText() );
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
	public void  uplo6(ActionEvent event) throws SQLException {
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Open File Dialog");

		Stage stage = (Stage)uploadResultA.getScene().getWindow();
		
		File file = filechooser.showOpenDialog(stage);
		String grade = createSubjectGradeChoice.getValue();
		
		String subjectID = null;
		String examID = null;
		
		String sql2 = "select subject_id from subject_details where grade = ? and subject_name = ? ";
		rst1 = executeQuery(sql2,  resultGrade.getValue(),  examSubject62.getText() );
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
	public void  uplo5(ActionEvent event) throws SQLException {
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Open File Dialog");

		Stage stage = (Stage)uploadResultA.getScene().getWindow();
		
		File file = filechooser.showOpenDialog(stage);
		String grade = createSubjectGradeChoice.getValue();
		
		String subjectID = null;
		String examID = null;
		
		String sql2 = "select subject_id from subject_details where grade = ? and subject_name = ? ";
		rst1 = executeQuery(sql2,  resultGrade.getValue(),  examSubject52.getText() );
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
	public void  uplo4(ActionEvent event) throws SQLException {
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Open File Dialog");

		Stage stage = (Stage)uploadResultA.getScene().getWindow();
		
		File file = filechooser.showOpenDialog(stage);
		String grade = createSubjectGradeChoice.getValue();
		
		String subjectID = null;
		String examID = null;
		
		String sql2 = "select subject_id from subject_details where grade = ? and subject_name = ? ";
		rst1 = executeQuery(sql2,  resultGrade.getValue(),  examSubject42.getText() );
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
	public void  uplo3(ActionEvent event) throws SQLException {
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Open File Dialog");

		Stage stage = (Stage)uploadResultA.getScene().getWindow();
		
		File file = filechooser.showOpenDialog(stage);
		String grade = createSubjectGradeChoice.getValue();
		
		String subjectID = null;
		String examID = null;
		
		String sql2 = "select subject_id from subject_details where grade = ? and subject_name = ? ";
		rst1 = executeQuery(sql2,  resultGrade.getValue(),  examSubject32.getText() );
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
	public void  uplo2(ActionEvent event) throws SQLException {
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Open File Dialog");

		Stage stage = (Stage)uploadResultA.getScene().getWindow();
		
		File file = filechooser.showOpenDialog(stage);
		String grade = createSubjectGradeChoice.getValue();
		
		String subjectID = null;
		String examID = null;
		
		String sql2 = "select subject_id from subject_details where grade = ? and subject_name = ? ";
		rst1 = executeQuery(sql2,  resultGrade.getValue(),  examSubject22.getText() );
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
	public void  uplo1(ActionEvent event) throws SQLException {
		
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Open File Dialog");

		Stage stage = (Stage)uploadResultA.getScene().getWindow();
		
		File file = filechooser.showOpenDialog(stage);
		String grade = createSubjectGradeChoice.getValue();
		
		String subjectID = null;
		String examID = null;
		
		String sql2 = "select subject_id from subject_details where grade = ? and subject_name = ? ";
		rst1 = executeQuery(sql2,  resultGrade.getValue(),  examSubject14.getText() );
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
	public void  uploadExcelStudents(ActionEvent event) {
		indexes3.clear();
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Open File Dialog");

		Stage stage = (Stage)createClassA.getScene().getWindow();
		
		File file = filechooser.showOpenDialog(stage);
		try (FileInputStream fis = new FileInputStream(file);
		         XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
		        
		        XSSFSheet sheet =  workbook.getSheetAt(0);
		        for (Row row : sheet) {
		            for (Cell cell : row) {
		                switch (cell.getCellType()) {
//		                    case STRING:
//		                        System.out.print(cell.getStringCellValue() + "\t");
//		                        break;
		                    case NUMERIC:
		                    	indexes3.add(String.valueOf((int)cell.getNumericCellValue()));
		                    	System.out.print(String.valueOf((int)cell.getNumericCellValue()) + "\t");
		                        break;
		                    default:
		                        break;
		                }
		                break;
		            }
		           
		        }
		    } catch (IOException e) {
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
	private void deleteInvoiceFromDatabase(String invoiceNumber) throws SQLException {
     
        String sql = "DELETE FROM payment_details WHERE invoice_number = ?"; // Update with your table and column names

        
        PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.setString(1, invoiceNumber);
        int affectedRows = pstmt.executeUpdate();
        
        if (affectedRows > 0) {
            System.out.println("Invoice deleted successfully.");
        } else {
            System.out.println("No invoice found with the given invoice number.");
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
//		System.out.println(position);
//		String position = null;
//		sql = "SELECT position FROM ( SELECT RANK() OVER (ORDER BY SUM(marks) DESC) AS position, student_id, SUM(marks) AS sum FROM `result_details` WHERE year = ? AND term = ? AND grade =? GROUP BY student_id ORDER BY sum DESC ) AS ranked_students WHERE student_id = ?;";
//		rst = executeQuery(sql, year, term, grade,id);
//		if(rst.next()) {
//			position = rst.getString("position");
//		}
		
		
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
	public void generatePymentSlip(String index, String invoice, String name, String date, String grade, 
			String amount, String note) throws IOException {
		
		String dest = "payment_invoiceOf"+index+".pdf";
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
            Paragraph header = new Paragraph("Kuli/ Sri Rewatha Rathanapala College\nStudent Payment Invoice", headerFont);
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

            // Add fields with placeholders
            String[] fields = {
                    "             Student Index Number:  "+invoice,
                    "             Invoice Number:        "+index,
                    "             Payment Name:          "+name,
                    "             Payment Date:          "+date,
                    "             Payment Grade:         "+grade,
                    "             Amount:                "+amount
            };

            for (String field : fields) {
                Paragraph paragraph = new Paragraph(field, font);
                paragraph.setSpacingAfter(10);
                document.add(paragraph);
            }

            LocalDate currentDate = LocalDate.now();
            int currentYear = currentDate.getYear();

            Paragraph signature = new Paragraph(
                    "………………… …………………………\nDate Principal\n" + currentYear, font);
            signature.setSpacingBefore(40);
            document.add(signature);

            // Close the document
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		stdNationality.getItems().addAll("Sinhala", "Muslim", "Tamil");
		stdReligion.getItems().addAll("Buddhist", "Islam", "Catholic");
		editStdNationality.getItems().addAll("Sinhala", "Muslim", "Tamil");
		editStdReligion.getItems().addAll("Buddhist", "Catholic", "Islam");
		paymenttype.getItems().addAll("Admission Fee","Library Fee","Sports Fee");
		paymenttype.setOnAction(this::fillPaymentName);
		paymenttype2.getItems().addAll("Admission Fee","Library Fee","Sports Fee");
	    for (int year = startYear; year <= endYear; year++) {
	        years.add(String.valueOf(year));
	    }
		paymentyear.getItems().addAll(years);

		createClassYearChoice.getItems().addAll(years);
		resultYear.getItems().addAll(years);
		
		createClassGradeChoice.getItems().addAll(grade);
		createClassGradeChoice.setOnAction(this::addTeachName);
		
		createClassClassChoice.getItems().addAll(clases);
		createSubjectYearChoice.getItems().addAll(years);
		perfClass.getItems().addAll(clases);
		perfGrade.getItems().addAll(grade);
		perfYear.getItems().addAll(years);
		
		classClass.getItems().addAll(clases);
		classGrade.getItems().addAll(grade);
		classGrade.setOnAction(this::addsubjects1);
		
		classYear.getItems().addAll(years);
		
		
		termplot.getItems().addAll(terms);
		yearplot.getItems().addAll(years);
		
		examyear.getItems().addAll(years);
		
		createSubjectGradeChoice.getItems().addAll(grade);
		createSubjectGradeChoice.setOnAction(this::addsubjects);
		
		examGrade.getItems().addAll(grade);
		examGrade.setOnAction(this::hidePrimary);
		
		resultGrade.getItems().addAll(grade);
		resultGrade.setOnAction(this::addSubjs);
		
		String[] gradeSec = {"6", "7", "8", "9", "10", "11" };
		selectGrade.getItems().addAll(gradeSec);
		selectGrade.setOnAction(this::addSubjects);
		selectSubject.setOnAction(this::addSubjectToBox);

		selectGrade1.getItems().addAll(gradeSec);
		selectGrade1.setOnAction(this::addSubjects1);
		selectSubject1.setOnAction(this::addSubjectToBox1);
		
		
		
		createSubjectClassChoice.getItems().addAll(clases);
//		resultClass.getItems().addAll(clases);
		
		paymentDetailsGradeChoice.getItems().addAll(grade);
		resultTerm.getItems().addAll(terms);
		
		

		String sql1 = "SELECT index_number FROM students_details";
		try {
			rst2 = executeQuery(sql1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			
			while(rst2.next()) {
				indexes2.add(rst2.getString("index_number"));
				
			}
//			stdindex.getItems().addAll(indexes2);
//			stdindex.setOnAction(this::fillId);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		String sql2 = "SELECT index_number FROM students_details";
		try {
			rst2 = executeQuery(sql2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			
			while(rst2.next()) {
				indexes.add(rst2.getString("index_number"));
				
			}
			stdName.getItems().addAll(indexes);
			stdName.setOnAction(this::fillstdfield);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		
//		
//		String sql3 = "SELECT registration_number FROM teacher_details";
//		try {
//			rst2 = executeQuery(sql3);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			
//			while(rst2.next()) {
//				regidx.add(rst2.getString("registration_number"));
//				
//			}
//			teachName.getItems().addAll(regidx);
//			teachName.setOnAction(this::fillteachfield);
//		
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
		
		
		
		

		sql2 = "SELECT index_number FROM students_details";
		try {
			rst2 = executeQuery(sql2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			
			while(rst2.next()) {
				indexes1.add(rst2.getString("index_number"));
				
			}
//			subjstud.getItems().addAll(indexes1);
//			subjstud.setOnAction(this::addsubjstud);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
//		
//		sql3 = "SELECT registration_number FROM teacher_details";
//		try {
//			rst2 = executeQuery(sql3);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			
//			while(rst2.next()) {
//				regidx1.add(rst2.getString("registration_number"));
//				
//			}
//			subjTeach.getItems().addAll(regidx1);
//			subjTeach.setOnAction(this::addsubteach);
//	
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		try{
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
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	public void fillPaymentName(ActionEvent event)  {
		
		paymentDetailsPaymentName.setText(paymenttype.getValue());
	
	}
	
	public void setmonths(ActionEvent event)  {
		examName1.getItems().clear();;
		examName1.getItems().addAll(monthL);
	
	}
	
	public void setterms(ActionEvent event)  {
		examName1.getItems().clear();;
		examName1.getItems().addAll(termL);
	
	}
	public void addTeachName(ActionEvent event)  {
		String grade = createClassGradeChoice.getValue();
		teachName.getItems().clear();
		ObservableList<String> temp= FXCollections.observableArrayList();
		if(grade.equals("1") || grade.equals("2") || grade.equals("3") || grade.equals("4") || grade.equals("5")) {
			try {
				String sql3 = "Select registration_number from teacher_details where current_grade = ?";
				rst2 = executeQuery(sql3, "1-5");
				
				while(rst2.next()) {
					temp.add(rst2.getString("registration_number"));
					
				}
				teachName.getItems().addAll(temp);
			}
			catch(SQLException e) {
				showAlert(Alert.AlertType.ERROR, "Error", "Teachers could not found");
			}
		}else {
			try {
				String sql3 = "Select registration_number from teacher_details where current_grade = ?";
				rst2 = executeQuery(sql3, grade);
				
				while(rst2.next()) {
					temp.add(rst2.getString("registration_number"));
					
				}
				teachName.getItems().addAll(temp);
			}
			catch(SQLException e) {
				showAlert(Alert.AlertType.ERROR, "Error", "Teachers could not found");
			}
		}
		
			
	}
	
	
	public void addSubjectToBox(ActionEvent event) {
		techCurrentSubject.setText(selectSubject.getValue());
	}
	public void addSubjectToBox1(ActionEvent event) {
		editTeacherCurrentSubject.setText(selectSubject1.getValue());
	}
	
	@FXML
	public void showPayHistory(ActionEvent event) throws SQLException {
		String payType = paymenttype2.getValue();
		String payyear = paymentyear.getValue();
		
		
		String indx = null;
		String sql0 = "select index_number from class_studentdetails where classID in (SELECT classID FROM `class_details` where year=?)";
		rst2 = executeQuery(sql0,  payyear);
		viewPaymentList.clear();
		while(rst2.next()){
			indx = rst2.getString("index_number");
			
			String sql1 = "select index_number from payment_details where payment_name = ? and index_number = ? and payment_grade = (SELECT grade FROM `class_details` where year=? and classID in  (select classID from class_studentdetails where index_number= ?))";
			rst1 = executeQuery(sql1, payType,indx,payyear,indx);
			
			
			// if there are payments
			if(!rst1.next()){
				viewPaymentList.add(new PaymentRecord(Integer.valueOf(indx), payType, "N/A", 0));
				viewPaymentTable.setItems(viewPaymentList);
			}
			
		}

		viewPaymentInvoice.setCellValueFactory(new PropertyValueFactory<PaymentRecord, Integer>("invoice"));
		viewPaymentName.setCellValueFactory(new PropertyValueFactory<PaymentRecord, String>("name"));
		viewPaymentPaymentDate.setCellValueFactory(new PropertyValueFactory<PaymentRecord, String>("date"));
		viewPaymentPaymentAmount.setCellValueFactory(new PropertyValueFactory<PaymentRecord, Integer>("amount"));
		
				
		
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

		String id = perfteachid.getText();
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
				  
//
//				String sql1 = "SELECT subject_name FROM `subject_details` where subject_id = ?";
//				rst1 = executeQuery(sql1, subj);
//				String subjName = null;
//				if(rst1.next()) {
//					subjName = rst1.getString("subject_name");
//				}
//				avgMarks = avgMarks/count;
//				XYChart.Series series1 = new XYChart.Series();
//				series1.setName(subjName);
//				series1.getData().add(new XYChart.Data(subjName, avgMarks));
//				subjObj.add(series1);
				
				
			
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
//					exammarks.add(avgMarks);
//					examhead.add(rst2.getString("grade")+"-"+rst2.getString("name"));
					
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
	
//	@FXML
//	public void searchResults(ActionEvent event) throws SQLException {
//		String subj = resultSubject.getValue();
//		String grade = resultGrade.getValue();
//		String clas = resultClass.getValue();
//		String year = resultYear.getValue();
//		String term = resultTerm.getValue();
//		
//		String sql2 = "SELECT subject_id FROM subject_details WHERE subject_name =? and grade= ? ";
//		
//		rst1 = executeQuery(sql2, subj, grade);
//		String subid = null;
//		if(rst1.next()) {
//			subid = String.valueOf(rst1.getInt("subject_id"));
//		}
//		
//		
//		String sql = "SELECT * FROM result_details where subj_id = ? and grade = ? and year = ? and term = ? and class =?";
//		rst = executeQuery(sql, subid, grade, year, term, clas);
//		
//		
//		resultList.clear();
//		// if there are payments
//		if(rst.next()){
//			sql = "SELECT * FROM result_details where subj_id = ? and grade = ? and year = ? and term = ? and class = ?";
//			rst = executeQuery(sql, subid, grade, year, term, clas);
//			while(rst.next()){
//				resultList.add(new ResultRecord(rst.getString("student_id"), rst.getString("marks")));
//				resultTempDetails.setItems(resultList);
//				
//			}
//			// set table columns
//			idxcol.setCellValueFactory(new PropertyValueFactory<ResultRecord, String>("no"));
//			marks.setCellValueFactory(new PropertyValueFactory<ResultRecord, String>("marks"));
//		}
//		
//	}
	
	
//	public void addresults(ActionEvent event) throws SQLException {
//		String subj = resultSubject.getValue();
//		String grade = resultGrade.getValue();
//		String clas = resultClass.getValue();
//		String year = resultYear.getValue();
//		String term = resultTerm.getValue();
//		String idx = resultIndexNumber.getText();
//		String marks = resultsMarks.getText();
//		
//		String sql2 = "SELECT subject_id FROM subject_details WHERE subject_name =? and grade= ? ";
//		
//		rst1 = executeQuery(sql2, subj, grade);
//		String subid = null;
//		if(rst1.next()) {
//			subid = String.valueOf(rst1.getInt("subject_id"));
//		}
//		
//		String sql = "INSERT INTO result_details(subj_id,	grade,	year,	term,	marks,	student_id, class) values(?,?,?,?,?,?,?)";
//		
//		int k = executeUpdate(sql, subid, grade, year, term, marks, idx, clas);
//		if (k>0) {
//			resultIndexNumber.clear();
//			resultsMarks.clear();
//			
//		}else {
//			showAlert(Alert.AlertType.ERROR, "Error", "Student not found");
//		}
//		
//		
//	}
//	public void fillId(ActionEvent event) {
//		resultIndexNumber.setText(stdindex.getValue());
//	}
	
//	public void addsubteach(ActionEvent event) {
//		createSubjectTeacherNO.setText(subjTeach.getValue());
//	}
	
//	public void addsubjstud(ActionEvent event) {
//		createSubjectStudentNO.setText(subjstud.getValue());
//	}
	
	public void addsubjects1(ActionEvent event) {
		String examGradeField = classGrade.getValue();
		classSub.getItems().clear();
		// if exam grade is empty, show an error message
		if(Integer.valueOf(examGradeField) > 9) {
			
			classSub.getItems().addAll(grade10_11Subjects);
		}else if(Integer.valueOf(examGradeField) > 5){
			
			classSub.getItems().addAll(grade6_7Subjects);
		}
		else {
			classSub.getItems().addAll(grade1_5Subjects);
		}
	} 
	

	public void addSubjects(ActionEvent event) {
		String examGradeField = selectGrade.getValue();
		techCurrentGrade.setText(examGradeField);
		
		selectSubject.getItems().clear();
		// if exam grade is empty, show an error message
		if(Integer.valueOf(examGradeField) > 9) {
			
			selectSubject.getItems().addAll(grade10_11Subjects);
		}else if(Integer.valueOf(examGradeField) > 5){
			
			selectSubject.getItems().addAll(grade6_7Subjects);
		}
		else {
			selectSubject.getItems().addAll(grade1_5Subjects);
		}
		
	} 
	
	public void addSubjects1(ActionEvent event) {
		String examGradeField = selectGrade1.getValue();
		editTeacherCurrentGrade.setText(examGradeField);
		
		selectSubject1.getItems().clear();
		// if exam grade is empty, show an error message
		if(Integer.valueOf(examGradeField) > 9) {
			
			selectSubject1.getItems().addAll(grade10_11Subjects);
		}else if(Integer.valueOf(examGradeField) > 5){
			
			selectSubject1.getItems().addAll(grade6_7Subjects);
		}
		else {
			selectSubject1.getItems().addAll(grade1_5Subjects);
		}
		
	} 
	
	public void addsubjects(ActionEvent event) {
		String examGradeField = createSubjectGradeChoice.getValue();
		
		subjTeach1.getItems().clear();
		subjTeach2.getItems().clear();
		subjTeach3.getItems().clear();
		subjTeach4.getItems().clear();
		subjTeach5.getItems().clear();
		subjTeach6.getItems().clear();
		subjTeach7.getItems().clear();
		subjTeach8.getItems().clear();
		subjTeach9.getItems().clear();
		subjTeach10.getItems().clear();
		subjTeach11.getItems().clear();
		subjTeach12.getItems().clear();
		
//		createSubjectSubject1Choice.getItems().clear();
		// if exam grade is empty, show an error message
		if(Integer.valueOf(examGradeField) > 9) {
			examSubject71.setVisible(true);
			examSubject81.setVisible(true);
			examSubject91.setVisible(true);
			examSubject101.setVisible(false);
			examSubject111.setVisible(false);
			examSubject121.setVisible(false);
			s71.setVisible(true);
			s81.setVisible(true);
			s91.setVisible(true);
			s101.setVisible(false);
			s111.setVisible(false);
			s121.setVisible(false);
			subjTeach7.setVisible(false);
			subjTeach8.setVisible(false);
			subjTeach9.setVisible(false);
			subjTeach10.setVisible(false);
			subjTeach11.setVisible(false);
			subjTeach12.setVisible(false);
			upload1.setVisible(true);
			upload2.setVisible(true);
			upload3.setVisible(true);
			
			
			examSubject13.setText("Sinhala");
			examSubject21.setText("Mathematics");
			examSubject31.setText("Religion");
			examSubject41.setText("History");
			examSubject51.setText("Science");
			examSubject61.setText("English");
			examSubject71.setText("Optional Subject1");
			examSubject81.setText("Optional Subject2");
			examSubject91.setText("Optional Subject3");
			
			try {
				ObservableList<String> temp= FXCollections.observableArrayList();
				String sql3 = "Select registration_number from teacher_subject where subject_id = (SELECT subject_id FROM subject_details where subject_name = ? and grade = ? )";
				rst2 = executeQuery(sql3,examSubject13.getText(), examGradeField);
				
				while(rst2.next()) {
					temp.add(rst2.getString("registration_number"));
					
				}
				subjTeach1.getItems().addAll(temp);
			}
			catch(SQLException e){
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Teachers!");
			}
			
			try {
				ObservableList<String> temp= FXCollections.observableArrayList();
				String sql3 = "Select registration_number from teacher_subject where subject_id = (SELECT subject_id FROM subject_details where subject_name = ? and grade = ? )";
				rst2 = executeQuery(sql3,examSubject21.getText(), examGradeField);
				
				while(rst2.next()) {
					temp.add(rst2.getString("registration_number"));
					
				}
				subjTeach2.getItems().addAll(temp);
			}
			catch(SQLException e){
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Teachers!");
			}
			try {
				ObservableList<String> temp= FXCollections.observableArrayList();
				String sql3 = "Select registration_number from teacher_subject where subject_id = (SELECT subject_id FROM subject_details where subject_name = ? and grade = ? )";
				rst2 = executeQuery(sql3,examSubject31.getText(), examGradeField);
				
				while(rst2.next()) {
					temp.add(rst2.getString("registration_number"));
					
				}
				subjTeach3.getItems().addAll(temp);
			}
			catch(SQLException e){
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Teachers!");
			}
			try {
				ObservableList<String> temp= FXCollections.observableArrayList();
				String sql3 = "Select registration_number from teacher_subject where subject_id = (SELECT subject_id FROM subject_details where subject_name = ? and grade = ? )";
				rst2 = executeQuery(sql3,examSubject41.getText(), examGradeField);
				
				while(rst2.next()) {
					temp.add(rst2.getString("registration_number"));
					
				}
				subjTeach4.getItems().addAll(temp);
			}
			catch(SQLException e){
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Teachers!");
			}
			try {
				ObservableList<String> temp= FXCollections.observableArrayList();
				String sql3 = "Select registration_number from teacher_subject where subject_id = (SELECT subject_id FROM subject_details where subject_name = ? and grade = ? )";
				rst2 = executeQuery(sql3,examSubject51.getText(), examGradeField);
				
				while(rst2.next()) {
					temp.add(rst2.getString("registration_number"));
					
				}
				subjTeach5.getItems().addAll(temp);
			}
			catch(SQLException e){
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Teachers!");
			}
			try {
				ObservableList<String> temp= FXCollections.observableArrayList();
				String sql3 = "Select registration_number from teacher_subject where subject_id = (SELECT subject_id FROM subject_details where subject_name = ? and grade = ? )";
				rst2 = executeQuery(sql3,examSubject61.getText(), examGradeField);
				
				while(rst2.next()) {
					temp.add(rst2.getString("registration_number"));
					
				}
				subjTeach6.getItems().addAll(temp);
			}
			catch(SQLException e){
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Teachers!");
			}
			
			

//			
//			subjTeach1
//			subjTeach2
//			subjTeach3
//			subjTeach4
//			subjTeach5
//			subjTeach6
//			subjTeach7
//			subjTeach8
//			subjTeach9
//			subjTeach10
//			subjTeach11
//			subjTeach12
//			
			upload1.setVisible(true);
			upload2.setVisible(true);
			upload3.setVisible(true);
			
			
		}else if(Integer.valueOf(examGradeField) > 5){
			examSubject71.setVisible(true);
			examSubject81.setVisible(true);
			examSubject91.setVisible(true);
			examSubject101.setVisible(true);
			examSubject111.setVisible(true);
			examSubject121.setVisible(true);
			s71.setVisible(true);
			s81.setVisible(true);
			s91.setVisible(true);
			s101.setVisible(true);
			s111.setVisible(true);
			s121.setVisible(true);
			subjTeach7.setVisible(true);
			subjTeach8.setVisible(true);
			subjTeach9.setVisible(true);
			subjTeach10.setVisible(true);
			subjTeach11.setVisible(true);
			subjTeach12.setVisible(true);
			upload1.setVisible(false);
			upload2.setVisible(false);
			upload3.setVisible(false);
			

			examSubject13.setText("Sinhala");
			examSubject21.setText("Mathematics");
			examSubject31.setText("Religion");
			examSubject41.setText("History");
			examSubject51.setText("Science");
			examSubject61.setText("English");
			examSubject71.setText("Tamil");
			examSubject81.setText("P.T.S.");
			examSubject91.setText("Civics");
			examSubject101.setText("Geography");
			examSubject111.setText("Health");
			examSubject121.setText("Aesthetic");
			
			
			try {
				ObservableList<String> temp= FXCollections.observableArrayList();
				String sql3 = "Select registration_number from teacher_subject where subject_id = (SELECT subject_id FROM subject_details where subject_name = ? and grade = ? )";
				rst2 = executeQuery(sql3,examSubject13.getText(), examGradeField);
				
				while(rst2.next()) {
					temp.add(rst2.getString("registration_number"));
					
				}
				subjTeach1.getItems().addAll(temp);
			}
			catch(SQLException e){
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Teachers!");
			}
			
			try {
				ObservableList<String> temp= FXCollections.observableArrayList();
				String sql3 = "Select registration_number from teacher_subject where subject_id = (SELECT subject_id FROM subject_details where subject_name = ? and grade = ? )";
				rst2 = executeQuery(sql3,examSubject21.getText(), examGradeField);
				
				while(rst2.next()) {
					temp.add(rst2.getString("registration_number"));
					
				}
				subjTeach2.getItems().addAll(temp);
			}
			catch(SQLException e){
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Teachers!");
			}
			try {
				ObservableList<String> temp= FXCollections.observableArrayList();
				String sql3 = "Select registration_number from teacher_subject where subject_id = (SELECT subject_id FROM subject_details where subject_name = ? and grade = ? )";
				rst2 = executeQuery(sql3,examSubject31.getText(), examGradeField);
				
				while(rst2.next()) {
					temp.add(rst2.getString("registration_number"));
					
				}
				subjTeach3.getItems().addAll(temp);
			}
			catch(SQLException e){
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Teachers!");
			}
			try {
				ObservableList<String> temp= FXCollections.observableArrayList();
				String sql3 = "Select registration_number from teacher_subject where subject_id = (SELECT subject_id FROM subject_details where subject_name = ? and grade = ? )";
				rst2 = executeQuery(sql3,examSubject41.getText(), examGradeField);
				
				while(rst2.next()) {
					temp.add(rst2.getString("registration_number"));
					
				}
				subjTeach4.getItems().addAll(temp);
			}
			catch(SQLException e){
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Teachers!");
			}
			try {
				ObservableList<String> temp= FXCollections.observableArrayList();
				String sql3 = "Select registration_number from teacher_subject where subject_id = (SELECT subject_id FROM subject_details where subject_name = ? and grade = ? )";
				rst2 = executeQuery(sql3,examSubject51.getText(), examGradeField);
				
				while(rst2.next()) {
					temp.add(rst2.getString("registration_number"));
					
				}
				subjTeach5.getItems().addAll(temp);
			}
			catch(SQLException e){
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Teachers!");
			}
			try {
				ObservableList<String> temp= FXCollections.observableArrayList();
				String sql3 = "Select registration_number from teacher_subject where subject_id = (SELECT subject_id FROM subject_details where subject_name = ? and grade = ? )";
				rst2 = executeQuery(sql3,examSubject61.getText(), examGradeField);
				
				while(rst2.next()) {
					temp.add(rst2.getString("registration_number"));
					
				}
				subjTeach6.getItems().addAll(temp);
			}
			catch(SQLException e){
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Teachers!");
			}
			try {
				ObservableList<String> temp= FXCollections.observableArrayList();
				String sql3 = "Select registration_number from teacher_subject where subject_id = (SELECT subject_id FROM subject_details where subject_name = ? and grade = ? )";
				rst2 = executeQuery(sql3,examSubject71.getText(), examGradeField);
				
				while(rst2.next()) {
					temp.add(rst2.getString("registration_number"));
					
				}
				subjTeach7.getItems().addAll(temp);
			}
			catch(SQLException e){
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Teachers!");
			}
			
			try {
				ObservableList<String> temp= FXCollections.observableArrayList();
				String sql3 = "Select registration_number from teacher_subject where subject_id = (SELECT subject_id FROM subject_details where subject_name = ? and grade = ? )";
				rst2 = executeQuery(sql3,examSubject81.getText(), examGradeField);
				
				while(rst2.next()) {
					temp.add(rst2.getString("registration_number"));
					
				}
				subjTeach8.getItems().addAll(temp);
			}
			catch(SQLException e){
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Teachers!");
			}
			try {
				ObservableList<String> temp= FXCollections.observableArrayList();
				String sql3 = "Select registration_number from teacher_subject where subject_id = (SELECT subject_id FROM subject_details where subject_name = ? and grade = ? )";
				rst2 = executeQuery(sql3,examSubject91.getText(), examGradeField);
				
				while(rst2.next()) {
					temp.add(rst2.getString("registration_number"));
					
				}
				subjTeach9.getItems().addAll(temp);
			}
			catch(SQLException e){
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Teachers!");
			}
			try {
				ObservableList<String> temp= FXCollections.observableArrayList();
				String sql3 = "Select registration_number from teacher_subject where subject_id = (SELECT subject_id FROM subject_details where subject_name = ? and grade = ? )";
				rst2 = executeQuery(sql3,examSubject101.getText(), examGradeField);
				
				while(rst2.next()) {
					temp.add(rst2.getString("registration_number"));
					
				}
				subjTeach10.getItems().addAll(temp);
			}
			catch(SQLException e){
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Teachers!");
			}
			try {
				ObservableList<String> temp= FXCollections.observableArrayList();
				String sql3 = "Select registration_number from teacher_subject where subject_id = (SELECT subject_id FROM subject_details where subject_name = ? and grade = ? )";
				rst2 = executeQuery(sql3,examSubject111.getText(), examGradeField);
				
				while(rst2.next()) {
					temp.add(rst2.getString("registration_number"));
					
				}
				subjTeach11.getItems().addAll(temp);
			}
			catch(SQLException e){
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Teachers!");
			}
			try {
				ObservableList<String> temp= FXCollections.observableArrayList();
				String sql3 = "Select registration_number from teacher_subject where subject_id = (SELECT subject_id FROM subject_details where subject_name = ? and grade = ? )";
				rst2 = executeQuery(sql3,examSubject121.getText(), examGradeField);
				
				while(rst2.next()) {
					temp.add(rst2.getString("registration_number"));
					
				}
				subjTeach12.getItems().addAll(temp);
			}
			catch(SQLException e){
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Teachers!");
			}
//			
//			subjTeach1
//			subjTeach2
//			subjTeach3
//			subjTeach4
//			subjTeach5
//			subjTeach6
//			subjTeach7
//			subjTeach8
//			subjTeach9
//			subjTeach10
//			subjTeach11
//			subjTeach12
		}
		else {
			examSubject71.setVisible(false);
			examSubject81.setVisible(false);
			examSubject91.setVisible(false);
			examSubject101.setVisible(false);
			examSubject111.setVisible(false);
			examSubject121.setVisible(false);
			s71.setVisible(false);
			s81.setVisible(false);
			s91.setVisible(false);
			s101.setVisible(false);
			s111.setVisible(false);
			s121.setVisible(false);
			subjTeach7.setVisible(false);
			subjTeach8.setVisible(false);
			subjTeach9.setVisible(false);
			subjTeach10.setVisible(false);
			subjTeach11.setVisible(false);
			subjTeach12.setVisible(false);
			upload1.setVisible(false);
			upload2.setVisible(false);
			upload3.setVisible(false);
			

			examSubject13.setText("Sinhala");
			examSubject21.setText("Mathematics");
			examSubject31.setText("Religion");
			examSubject41.setText("Tamil");
			examSubject51.setText("Environment");
			examSubject61.setText("English");
			
			try {
				ObservableList<String> temp= FXCollections.observableArrayList();
				String sql3 = "Select registration_number from teacher_subject where subject_id = (SELECT subject_id FROM subject_details where subject_name = ? and grade = ? )";
				rst2 = executeQuery(sql3,examSubject13.getText(), examGradeField);
				
				while(rst2.next()) {
					temp.add(rst2.getString("registration_number"));
					
				}
				subjTeach1.getItems().addAll(temp);
			}
			catch(SQLException e){
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Teachers!");
			}
			
			try {
				ObservableList<String> temp= FXCollections.observableArrayList();
				String sql3 = "Select registration_number from teacher_subject where subject_id = (SELECT subject_id FROM subject_details where subject_name = ? and grade = ? )";
				rst2 = executeQuery(sql3,examSubject21.getText(), examGradeField);
				
				while(rst2.next()) {
					temp.add(rst2.getString("registration_number"));
					
				}
				subjTeach2.getItems().addAll(temp);
			}
			catch(SQLException e){
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Teachers!");
			}
			try {
				ObservableList<String> temp= FXCollections.observableArrayList();
				String sql3 = "Select registration_number from teacher_subject where subject_id = (SELECT subject_id FROM subject_details where subject_name = ? and grade = ? )";
				rst2 = executeQuery(sql3,examSubject31.getText(), examGradeField);
				
				while(rst2.next()) {
					temp.add(rst2.getString("registration_number"));
					
				}
				subjTeach3.getItems().addAll(temp);
			}
			catch(SQLException e){
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Teachers!");
			}
			try {
				ObservableList<String> temp= FXCollections.observableArrayList();
				String sql3 = "Select registration_number from teacher_subject where subject_id = (SELECT subject_id FROM subject_details where subject_name = ? and grade = ? )";
				rst2 = executeQuery(sql3,examSubject41.getText(), examGradeField);
				
				while(rst2.next()) {
					temp.add(rst2.getString("registration_number"));
					
				}
				subjTeach4.getItems().addAll(temp);
			}
			catch(SQLException e){
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Teachers!");
			}
			try {
				ObservableList<String> temp= FXCollections.observableArrayList();
				String sql3 = "Select registration_number from teacher_subject where subject_id = (SELECT subject_id FROM subject_details where subject_name = ? and grade = ? )";
				rst2 = executeQuery(sql3,examSubject51.getText(), examGradeField);
				
				while(rst2.next()) {
					temp.add(rst2.getString("registration_number"));
					
				}
				subjTeach5.getItems().addAll(temp);
			}
			catch(SQLException e){
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Teachers!");
			}
			try {
				ObservableList<String> temp= FXCollections.observableArrayList();
				String sql3 = "Select registration_number from teacher_subject where subject_id = (SELECT subject_id FROM subject_details where subject_name = ? and grade = ? )";
				rst2 = executeQuery(sql3,examSubject61.getText(), examGradeField);
				
				while(rst2.next()) {
					temp.add(rst2.getString("registration_number"));
					
				}
				subjTeach6.getItems().addAll(temp);
			}
			catch(SQLException e){
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Teachers!");
			}
//			
//			subjTeach1
//			subjTeach2
//			subjTeach3
//			subjTeach4
//			subjTeach5
//			subjTeach6
//			subjTeach7
//			subjTeach8
//			subjTeach9
//			subjTeach10
//			subjTeach11
//			subjTeach121
		}
	} 

	
	
	public void fillstdfield(ActionEvent event) {
		createClassStudentNO.setText(stdName.getValue());
	}
	public void fillteachfield(ActionEvent event) {
//		createClassTeacherNO.setText(teachName.getValue());
	}
	
	
	public void addSubjs(ActionEvent event) {
		String examGradeField = resultGrade.getValue();
		
		
//		createSubjectSubject1Choice.getItems().clear();
		// if exam grade is empty, show an error message
		if(Integer.valueOf(examGradeField) > 9) {
			examSubject72.setVisible(true);
			examSubject82.setVisible(true);
			examSubject92.setVisible(true);
			examSubject102.setVisible(false);
			examSubject112.setVisible(false);
			examSubject122.setVisible(false);
			s72.setVisible(true);
			s82.setVisible(true);
			s92.setVisible(true);
			s102.setVisible(false);
			s112.setVisible(false);
			s122.setVisible(false);
			u7.setVisible(true);
			u8.setVisible(true);
			u9.setVisible(true);
			u10.setVisible(false);
			u11.setVisible(false);
			u12.setVisible(false);
			
			
			examSubject14.setText("Sinhala");
			examSubject22.setText("Mathematics");
			examSubject32.setText("Religion");
			examSubject42.setText("History");
			examSubject52.setText("Science");
			examSubject62.setText("English");
			examSubject72.setText("Optional Subject1");
			examSubject82.setText("Optional Subject2");
			examSubject92.setText("Optional Subject3");
			
			
			
		}else if(Integer.valueOf(examGradeField) > 5){
			examSubject72.setVisible(true);
			examSubject82.setVisible(true);
			examSubject92.setVisible(true);
			examSubject102.setVisible(true);
			examSubject112.setVisible(true);
			examSubject122.setVisible(true);
			s72.setVisible(true);
			s82.setVisible(true);
			s92.setVisible(true);
			s102.setVisible(true);
			s112.setVisible(true);
			s122.setVisible(true);
			u7.setVisible(true);
			u8.setVisible(true);
			u9.setVisible(true);
			u10.setVisible(true);
			u11.setVisible(true);
			u12.setVisible(true);
			
			

			examSubject14.setText("Sinhala");
			examSubject22.setText("Mathematics");
			examSubject32.setText("Religion");
			examSubject42.setText("History");
			examSubject52.setText("Science");
			examSubject62.setText("English");
			examSubject72.setText("Tamil");
			examSubject82.setText("P.T.S.");
			examSubject92.setText("Civics");
			examSubject102.setText("Geography");
			examSubject112.setText("Health");
			examSubject122.setText("Aesthetic");
			
			
		}
		else {
			examSubject72.setVisible(false);
			examSubject82.setVisible(false);
			examSubject92.setVisible(false);
			examSubject102.setVisible(false);
			examSubject112.setVisible(false);
			examSubject122.setVisible(false);
			s72.setVisible(false);
			s82.setVisible(false);
			s92.setVisible(false);
			s102.setVisible(false);
			s112.setVisible(false);
			s122.setVisible(false);
			u7.setVisible(false);
			u8.setVisible(false);
			u9.setVisible(false);
			u10.setVisible(false);
			u11.setVisible(false);
			u12.setVisible(false);
			
			

			examSubject14.setText("Sinhala");
			examSubject22.setText("Mathematics");
			examSubject32.setText("Religion");
			examSubject42.setText("Tamil");
			examSubject52.setText("Environment");
			examSubject62.setText("English");
			
		}
	}
	
	@FXML
	public void visibleGrade(ActionEvent event) {
		techCurrentGrade.setVisible(true);
		teachSubjLabel.setVisible(true);
		techCurrentSubject.setVisible(true);
		
		techCurrentGrade.clear();
		techCurrentSubject.clear();
		
		selectGrade.setVisible(true);
		selectSubject.setVisible(true);
		
		teachPrimary.setSelected(false);
		
	}
	@FXML
	public void hideGrade(ActionEvent event) {
		techCurrentGrade.setVisible(false);
		teachSubjLabel.setVisible(false);
		techCurrentSubject.setVisible(false);
		techCurrentGrade.setText("1-5");
		techCurrentSubject.setText("All");

		selectGrade.setVisible(false);
		selectSubject.setVisible(false);
		

		
		
		teachSecondary.setSelected(false);
	}
	
	@FXML
	public void visibleGrade1(ActionEvent event) {
		editTeacherCurrentGrade.setVisible(true);
		gradeLabel1.setVisible(true);
		teachSubjLabel1.setVisible(true);
		editTeacherCurrentSubject.setVisible(true);
		selectGrade1.setVisible(true);
		selectSubject1.setVisible(true);
		
		
		
		
		teachPrimary1.setSelected(false);
		
	}
	@FXML
	public void hideGrade1(ActionEvent event) {
		editTeacherCurrentGrade.setVisible(false);
		gradeLabel1.setVisible(false);
		teachSubjLabel1.setVisible(false);
		editTeacherCurrentSubject.setVisible(false);
		
		editTeacherCurrentGrade.setText("1-5");
		editTeacherCurrentSubject.setText("All");
		
		selectGrade1.setVisible(false);
		selectSubject1.setVisible(false);
		
		
		teachSecondary1.setSelected(false);
	}
	public void hidePrimary(ActionEvent event) {
		String examGradeField = examGrade.getValue();
		// if exam grade is empty, show an error message
		if(Integer.valueOf(examGradeField) > 9) {
			System.out.println(examGradeField);
			examSubject12.setVisible(false);
			examDate12.setVisible(false);
			examTime12.setVisible(false);
			examSubject11.setVisible(false);
			examDate11.setVisible(false);
			examTime11.setVisible(false);
			examSubject10.setVisible(false);
			examDate10.setVisible(false);
			examTime10.setVisible(false);
			
			examSubject9.setVisible(true);
			examDate9.setVisible(true);
			examTime9.setVisible(true);
			examSubject8.setVisible(true);
			examDate8.setVisible(true);
			examTime8.setVisible(true);
			examSubject7.setVisible(true);
			examDate7.setVisible(true);
			examTime7.setVisible(true);
			
			s7.setVisible(true);
			s8.setVisible(true);
			s9.setVisible(true);
			s10.setVisible(false);
			s11.setVisible(false);
			s12.setVisible(false);
			
			
			examSubject1.setText("Sinhala");
			examSubject2.setText("Mathematics");
			examSubject3.setText("Religion");
			examSubject4.setText("History");
			examSubject5.setText("Science");
			examSubject6.setText("English");
			examSubject7.setText("Optional Subject1");
			examSubject8.setText("Optional Subject2");
			examSubject9.setText("Optional Subject3");
			 

		}else if(Integer.valueOf(examGradeField) > 5){
			System.out.println(examGradeField);
			examSubject12.setVisible(true);
			examDate12.setVisible(true);
			examTime12.setVisible(true);
			examSubject11.setVisible(true);
			examDate11.setVisible(true);
			examTime11.setVisible(true);
			examSubject10.setVisible(true);
			examDate10.setVisible(true);
			examTime10.setVisible(true);
			
			examSubject9.setVisible(true);
			examDate9.setVisible(true);
			examTime9.setVisible(true);
			examSubject8.setVisible(true);
			examDate8.setVisible(true);
			examTime8.setVisible(true);
			examSubject7.setVisible(true);
			examDate7.setVisible(true);
			examTime7.setVisible(true);
			
			s7.setVisible(true);
			s8.setVisible(true);
			s9.setVisible(true);
			s10.setVisible(true);
			s11.setVisible(true);
			s12.setVisible(true);
			
			
			examSubject1.setText("Sinhala");
			examSubject2.setText("Mathematics");
			examSubject3.setText("Religion");
			examSubject4.setText("History");
			examSubject5.setText("Science");
			examSubject6.setText("English");
			examSubject7.setText("Tamil");
			examSubject8.setText("P.T.S.");
			examSubject9.setText("Civics");
			examSubject10.setText("Geography");
			examSubject11.setText("Health");
			examSubject12.setText("Aesthetic");
			
			
		}
		else {
			System.out.println(examGradeField);
			examSubject12.setVisible(false);
			examDate12.setVisible(false);
			examTime12.setVisible(false);
			examSubject11.setVisible(false);
			examDate11.setVisible(false);
			examTime11.setVisible(false);
			examSubject10.setVisible(false);
			examDate10.setVisible(false);
			examTime10.setVisible(false);
			
			examSubject9.setVisible(false);
			examDate9.setVisible(false);
			examTime9.setVisible(false);
			examSubject8.setVisible(false);
			examDate8.setVisible(false);
			examTime8.setVisible(false);
			examSubject7.setVisible(false);
			examDate7.setVisible(false);
			examTime7.setVisible(false);
			
			s7.setVisible(false);
			s8.setVisible(false);
			s9.setVisible(false);
			s10.setVisible(false);
			s11.setVisible(false);
			s12.setVisible(false);
			
			examSubject1.setText("Sinhala");
			examSubject2.setText("Mathematics");
			examSubject3.setText("Religion");
			examSubject4.setText("Tamil");
			examSubject5.setText("Environment");
			examSubject6.setText("English");
			
			}
	} 

}