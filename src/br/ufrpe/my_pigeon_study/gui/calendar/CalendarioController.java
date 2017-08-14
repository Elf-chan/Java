package br.ufrpe.my_pigeon_study.gui.calendar;

import java.io.IOException;

import com.jfoenix.controls.JFXDatePicker;

import Exceptions.InformacaoInvalidaException;
import br.ufrpe.my_pigeon_study.gui.ScreenManager;
import br.ufrpe.my_pigeon_study.gui.login.LoginController;

import br.ufrpe.my_pigeon_study.negocio.Fachada;
import br.ufrpe.my_pigeon_study.negocio.beans.Disciplina;
import br.ufrpe.my_pigeon_study.negocio.beans.Task;
import br.ufrpe.my_pigeon_study.negocio.beans.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;


public class CalendarioController {
	
	private Fachada fachada;
	
	private static Usuario user;
	
	private static Task task;
	
	private static Disciplina disciplina;
	
	@FXML
    private Label userName;

    @FXML
    private JFXDatePicker date;

    @FXML
    private ListView<String> classesList;

    @FXML
    private ListView<String> taskList;
    private final String[] opcoes={"Ascending","Descending"};
   
    @FXML
    private ComboBox<String> comboSort;
    
    @FXML
    private Label nClass;

    @FXML
    private Label nTask;
    
    public static Task getTask() {
    	return task;
    }
    public static void setTask(Task task) {
    	CalendarioController.task = task;
    }
    
    public static Disciplina getDisciplina() {
		return disciplina;
	}
	public static void setDisciplina(Disciplina disciplina) {
		CalendarioController.disciplina = disciplina;
	}
	public CalendarioController() throws IOException{
    	this.fachada = Fachada.getInstancia();
    }
    
    public void setUser(Usuario useri){
    	user = useri;
    }
    public static Usuario getUser(){
    	return(user);
    }
    
    
    @FXML
	private void initialize() throws InformacaoInvalidaException 
	{
    	Usuario c = this.fachada.buscar(LoginController.getUser().getUsuario());
    	this.setUser(c);
    	userName.setText(c.getNome());
    	comboSort.getItems().addAll(opcoes);
    	comboSort.setValue(opcoes[0]);
    	comboSort.setOnAction(e->getOption());
    	
    	taskList.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override//Quando selecionar no listview a task 
            public void handle(MouseEvent event) {
        		Task t = fachada.buscarTask(user, taskList.getSelectionModel().getSelectedItem());
        		task = t;
        		try {
        			chamarTaskInfo();
				} catch (IOException e) {
					e.printStackTrace();
				}
                
            }

			private void chamarTaskInfo() throws IOException {
				ScreenManager.getInstance().showInfoTask();
				
			}
        });
    	classesList.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override//Quando selecionar no listview a disicplina 
            public void handle(MouseEvent event) {
        		Disciplina d = fachada.buscarAtividade(user, taskList.getSelectionModel().getSelectedItem());
        		disciplina = d;
        		try {
        			chamarDisciplinaInfo();
				} catch (IOException e) {
					e.printStackTrace();
				}
                
            }

			private void chamarDisciplinaInfo() throws IOException {
				ScreenManager.getInstance().showInfoDisciplina();
				
			}
        });
    	
	}
	
	private void getOption() {
		
		listar();
	}
	@FXML
    void editTask() throws IOException{
        
    }
    @FXML
    private void listar() {
    	ObservableList<String> classes = FXCollections.observableArrayList(fachada.calendarioAtividades(user, date.getValue().getDayOfWeek().getValue()));
    	if(comboSort.getValue()==opcoes[0]){
    		classesList.setItems(classes.sorted());
    	}
    	else{
    		classesList.setItems(classes);
    	}
    	nClass.setText("(" + classes.size() + ")");
    	
    	ObservableList<String> tasks = FXCollections.observableArrayList(fachada.calendarioTasks(user, date.getValue()));
    	if(comboSort.getValue()==opcoes[0]){
    		taskList.setItems(tasks.sorted());
    	}
    	else{
    		taskList.setItems(tasks);
    	}
    	nTask.setText("("+ tasks.size() +")");
    }
    @FXML
    void chamarTelaInicial(){
    	ScreenManager.getInstance().showLogin();
    }
    @FXML
    void chamarTelaTask(){
    	ScreenManager.getInstance().showMainTask();
    }
    
    @FXML
    void chamarTelaCalendar(){
    	ScreenManager.getInstance().showCalendario();
    }
    @FXML
    void chamarTelaProfile(){
    	ScreenManager.getInstance().showPerfil();
    }
    @FXML
    void chamarTelaDisciplina(){
    	ScreenManager.getInstance().showMainDisciplina();
    }
}

