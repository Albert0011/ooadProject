package ooad;



import controllers.MainController;

public class Main {
	
//	main
	public Main() {
		MainController.getInstance().displayLogin();
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
}