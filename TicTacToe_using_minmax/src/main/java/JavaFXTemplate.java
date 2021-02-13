
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
 * Daniela Chavez
 * CS342
 * UIC Fall2020(Corona Time)
 * Project 4
 * */
public class JavaFXTemplate extends Application {
	
	static GridPane grid = new GridPane();
	static Board gameBoard;
	static Scene WelcomeScene,introScene, InstructionScene, GameScene, TheUserChoises, theActualGame, weirdTransitionScene, endScene;
	static Button Instructions, PlayGame,Exit,Back,StartGame, PlayAgain;
	static String[] Levels = {"Novice", "Advanced", "Expert"};
	static GridPane XLevel;
	static GridPane OLevel; 
	static int rounds = 0;
	static String[] testing = {"a", "b", "c", "d", "e", "f","g", "h", "i"};
	static String levelX = "";
	static String levelO = "";
	static Text T1,T2,T3,T4,T5,T6,T7,T8,T9;
	static int h = 0;
	static Pane theGame = new Pane();
	static Pane UserPick = new Pane();
	static String Winner = " ";
	static int roundCounter = 1;
	static Text XPoints = new Text("0");
	static Text OPoints = new Text("0");
	static Pane endPane = new Pane();
	static Pane whoWonTheGame = new Pane();
	static int xpoints = 0;
	static int opoints = 0;
	

	
	static Vector<Integer> IndexInOrder = new Vector<Integer>();
	static Vector<String> CharacterInOrder = new Vector<String>();
	
	
	void endScene(Stage primaryStage)
	{
		theGame.getChildren().clear();
		endPane.getChildren().clear();
		roundCounter = 1;
		IndexInOrder.clear();
		CharacterInOrder.clear();
		Image dummy = new Image("WhoWon.png");
		ImageView theDummy = new ImageView(dummy);
		grid.setDisable(false);
		XLevel.setDisable(true);
		OLevel.setDisable(true);
		StartGame.setDisable(true);
		
		
		if(gameBoard.DidXWin() == true)
		{
			Winner = " X ";
			xpoints++;
		}
			
		else if (gameBoard.DidOWin() == true) 
		{
			Winner = " O ";
			opoints++;
		}
		
		
		
		Text totalXPoints = new Text();
		totalXPoints.setText("The total number of points for X: " + String.valueOf(xpoints));
		
		Text totalOPoints = new Text();
		totalOPoints.setText("The total number of points for O: " +String.valueOf(opoints));
		
		Text results = new Text("And the winner after " +String.valueOf(rounds)+ " rounds is -> ");
		Text winneris = new Text();
		
		if(xpoints > opoints)
			winneris.setText(" X");
		else if (xpoints < opoints)
			winneris.setText(" O");
		else
			winneris.setText("Its a tie!");
		
		
		Text EndMsg =  new Text("The game is over");
		EndMsg.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
		EndMsg.setLayoutX(67);
		EndMsg.setLayoutY(200);
		

		Button PlayAgain = new Button ("Play Again");
		PlayAgain.setOnAction(PA->
		{
			primaryStage.setScene(TheUserChoises);
			primaryStage.show();
			
		});
		
		PlayAgain.setLayoutX(130); PlayAgain.setLayoutY(340);
		PlayAgain.setStyle("-fx-background-color: #2cddb9; " //Setting the style for the buttons
				+ "-fx-border-color: #000000; "
				+ "-fx-border-width: 2px; "
				+ "-fx-font-size: 1.3em; ");
		
		Button Exit = new Button ("Exit");
		Exit.setOnAction(E->{
			primaryStage.close();
		});
		
		Exit.setLayoutX(300); Exit.setLayoutY(340);
		Exit.setStyle("-fx-background-color: #ef2548; " //Setting the style for the buttons
				+ "-fx-border-color: #000000; "
				+ "-fx-border-width: 2px; "
				+ "-fx-font-size: 1.3em; ");
		
		
		totalXPoints.setLayoutY(250);totalXPoints.setLayoutX(120);
		totalOPoints.setLayoutY(270);totalOPoints.setLayoutX(120);
		results.setLayoutY(290);results.setLayoutX(70);
		winneris.setLayoutY(290);winneris.setLayoutX(373);
		
		totalXPoints.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		totalOPoints.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		results.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
		
		
		endPane.getChildren().clear();
		endPane.getChildren().addAll(theDummy, EndMsg, PlayAgain, Exit, totalXPoints,totalOPoints,results,winneris);
		
		xpoints = 0;
		opoints = 0;
		XPoints.setText(String.valueOf(xpoints));
		OPoints.setText(String.valueOf(xpoints));
		
		
		
		primaryStage.setScene(endScene);
		primaryStage.show();
		
	}
	
	
	void weirdTransition(Stage primaryStage) 
	{
		
		theGame.getChildren().clear();
		IndexInOrder.clear();
		CharacterInOrder.clear();
		
		Image dummy = new Image("WhoWon.png");
		ImageView theDummy = new ImageView(dummy);
		
		
		if(gameBoard.DidXWin() == true)
		{
			Winner = " Player X ";
			xpoints++;
			XPoints.setText(String.valueOf(xpoints));
		}
			
		else if (gameBoard.DidOWin() == true) 
		{
			Winner = " Player O ";
			opoints++;
			OPoints.setText(String.valueOf(opoints));
		}
			
		else
			Winner = "Nobody! It's a tie.";
		
		
		Text dummyWinner = new Text(Winner);
		dummyWinner.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
		dummyWinner.setLayoutX(150);
		dummyWinner.setLayoutY(270);
		
		
		Text theWinner =  new Text("And the winner is... ");
		theWinner.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
		theWinner.setLayoutX(80);
		theWinner.setLayoutY(230);
		whoWonTheGame.getChildren().clear();
		whoWonTheGame.getChildren().addAll(theDummy, theWinner, dummyWinner);

		PauseTransition weird = new PauseTransition(Duration.seconds(2));
		weird.setOnFinished(WS->{
			roundCounter++;
			primaryStage.setScene(TheUserChoises);
			primaryStage.show();
		}); 
		
		primaryStage.setScene(weirdTransitionScene);
		weird.play();
		primaryStage.show();
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		launch(args);
	}
	
	
	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		
		//------------------------------------------------------------------------------------------------------
		weirdTransitionScene = new Scene(whoWonTheGame, 518, 432);
		endScene = new Scene(endPane, 518, 432);
		theActualGame = new Scene(theGame,596, 350);
		TheUserChoises = new Scene(UserPick, 372, 558);
	

		//---------------------------START GAME-------------------------------------------------------------------
		StartGame = new Button("Start Game");
		StartGame.setStyle("-fx-background-color: #ef4136; -fx-border-color: #000000; -fx-border-width: 2px; -fx-font-size: 2em; ");
		StartGame.setLayoutX(113);
		StartGame.setLayoutY(430);
		StartGame.setDisable(true);
		
		XPoints.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 60));
		OPoints.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 60));
		
		
		XPoints.setLayoutX(455);XPoints.setLayoutY(175);
		OPoints.setLayoutX(455);OPoints.setLayoutY(295);
		
		StartGame.setOnAction(SG->
		{
			
			 //Creating new pane to manage all images and buttons
			
			//Inserting image as a background...
			Image gameMode = new Image("TicTacToe.png");
			ImageView newImage = new ImageView(gameMode);
			
			
			gameBoard = new Board();
			ExecutorService X = Executors.newFixedThreadPool(4);
			Integer turn = 1;
			
			int counter = 0;
			boolean gameWin = true;
			
			while(gameWin) {
				
				Future<Integer> future1 = X.submit(new MyCall(gameBoard.board, turn, levelX, levelO, counter));
			
				try {
				Integer index = future1.get();
				
				if (turn == 1) { 
					IndexInOrder.add(index);
					CharacterInOrder.add("x");
					gameBoard.board[index] = "x";
					
				}
				else {
					IndexInOrder.add(index);
					CharacterInOrder.add("o");
					gameBoard.board[index] = "o";
				}
	
				if (gameBoard.DidXWin()) {

					gameWin = false;
				}
				
				else if (gameBoard.DidOWin()) 
				{
					gameWin = false;
				}
				
				if (counter == 8) {
					gameWin = false;
				}
				counter++;
				}catch(Exception e){System.out.println(e.getMessage());}
				
				//------------------------------------------------------------------------------------
				
				if(turn == 1) 
					turn = 0;
				else 
					turn = 1;
			}
			X.shutdown();
		
			counter = 0;
			//----------------------------------------------------------------------------------------
			
			
			T1= new Text(".");T2= new Text(".");T3= new Text(".");
			T4= new Text(".");T5= new Text(".");T6= new Text(".");
			T7= new Text(".");T8= new Text(".");T9= new Text(".");
			
			
			//Initializing the Texts

			
			
			//-----------------------------------------------------------------------
			T1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 100));
			T2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 100));
			T3.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 100));
			T4.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 100));
			T5.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 100));
			T6.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 100));
			T7.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 100));
			T8.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 100));
			T9.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 100));
			
			//-----------------------------------------------------------------------
			//Setting layout for the Text's			
			T1.setLayoutX(65); T1.setLayoutY(115);
			T2.setLayoutX(165); T2.setLayoutY(115);
			T3.setLayoutX(265); T3.setLayoutY(115);
			
			T4.setLayoutX(65); T4.setLayoutY(215);
			T5.setLayoutX(165); T5.setLayoutY(215);
			T6.setLayoutX(265); T6.setLayoutY(215);
			
			T7.setLayoutX(65); T7.setLayoutY(315);
			T8.setLayoutX(165); T8.setLayoutY(315);
			T9.setLayoutX(265); T9.setLayoutY(315);
			
			
			
			//Now that we have the transitions.... 
			//-----------------------------------------------------------------------------------------
			
			for(int i = 0; i<IndexInOrder.size(); i++) 
			{
				PauseTransition printBoard = new PauseTransition(Duration.seconds(i));
				
				if(CharacterInOrder.get(i) == "x") 
				{
					if(IndexInOrder.get(i) == 0)
						printBoard.setOnFinished(A->{T1.setText("x");});
					else if(IndexInOrder.get(i) == 1)
						printBoard.setOnFinished(B->{T2.setText("x");});
			
					else if(IndexInOrder.get(i) == 2)
						printBoard.setOnFinished(C->{T3.setText("x");});

					else if(IndexInOrder.get(i) == 3)
						printBoard.setOnFinished(D->{T4.setText("x");});

					else if(IndexInOrder.get(i) == 4)
						printBoard.setOnFinished(E->{T5.setText("x");});

					else if(IndexInOrder.get(i) == 5)
						printBoard.setOnFinished(F->{T6.setText("x");});

					else if(IndexInOrder.get(i) == 6)
						printBoard.setOnFinished(G->{T7.setText("x");});

					else if(IndexInOrder.get(i) == 7)
						printBoard.setOnFinished(H->{T8.setText("x");});

					else
						printBoard.setOnFinished(I->{T9.setText("x");});
				}
				
				else if (CharacterInOrder.get(i) == "o")
				{
					if(IndexInOrder.get(i) == 0)
						printBoard.setOnFinished(J->{T1.setText("o");});
					else if(IndexInOrder.get(i) == 1)
						printBoard.setOnFinished(K->{T2.setText("o");});
					else if(IndexInOrder.get(i) == 2)
						printBoard.setOnFinished(L->{T3.setText("o");});
					else if(IndexInOrder.get(i) == 3)
						printBoard.setOnFinished(M->{T4.setText("o");});

					else if(IndexInOrder.get(i) == 4)
						printBoard.setOnFinished(N->{T5.setText("o");});

					else if(IndexInOrder.get(i) == 5)
						printBoard.setOnFinished(O->{T6.setText("o");});
	
					else if(IndexInOrder.get(i) == 6)
						printBoard.setOnFinished(P->{T7.setText("o");});
		
					else if(IndexInOrder.get(i) == 7)
						printBoard.setOnFinished(Q->{T8.setText("o");});

					
					else
						printBoard.setOnFinished(R->{T9.setText("o");});
				}
				
				printBoard.play();
			}
			
			theGame.getChildren().clear();
			theGame.getChildren().addAll(newImage, T1, T2, T3,T4,T5,T6,T7,T8,T9, XPoints, OPoints);
			
			PauseTransition roundPause = new PauseTransition(Duration.seconds(9));
			
			if(roundCounter < rounds)
				roundPause.setOnFinished(WS->{weirdTransition(primaryStage);});
			else
				roundPause.setOnFinished(ES->{endScene(primaryStage);});

			
			primaryStage.setScene(theActualGame);
			primaryStage.show();
			roundPause.play();
			

		});
		
		//--------------------------------------------------------------------------------------------------
		Exit = new Button("Exit");
		Exit.setStyle("-fx-background-color: #fd003c; -fx-border-color: #000000; -fx-border-width: 2px; -fx-font-size: 2em; ");
		Exit.setLayoutX(453);
		Exit.setLayoutY(250);
		Exit.setOnAction(E->
		{
			primaryStage.close();
		});
		
		//--------------------------------------------------------------------------------------------------
		//Creating the Back button...
		//This will allow the user to go "back" to the previous scene...
		Back = new Button ("Go Back");
		Back.setStyle("-fx-background-color: #bd4181; -fx-border-color: #000000; -fx-border-width: 2px; -fx-font-size: 2em; ");
		Back.setLayoutX(425);
		Back.setLayoutY(180);
		Back.setOnAction(B->
		
		{
			primaryStage.setScene(introScene);
			primaryStage.show();
		});
		//--------------------------------------------------------------------------------------------------
		
		//Creating the new buttons...
		//Creating the new instructions button with it's event handler...
		Instructions = new Button("How To Play");
		Instructions.setStyle("-fx-background-color: #ffcd08; -fx-border-color: #000000; -fx-border-width: 2px; -fx-font-size: 2em; ");
		Instructions.setLayoutX(404);
		Instructions.setLayoutY(110);
		Instructions.setOnAction(IS->    //Creating the event hanlder for the instruction scene
		{
			Image InstructionsScene = new Image("InstructionsScene.png");
			ImageView InsScene = new ImageView(InstructionsScene);
			
			Pane InstrucPane = new Pane();
			
			InstrucPane.getChildren().addAll(InsScene, Back);
			
			InstructionScene = new Scene(InstrucPane,596, 350);
			
			primaryStage.setScene(InstructionScene);
			primaryStage.show();
			
		});
		
		//--------------------------------------------------------------------------------------------------
		//This is creating the play game scene... where we will display the options the user must pick...
		PlayGame = new Button ("Play Game!");
		//Deciding the layout of the button on the new pane
		PlayGame.setStyle("-fx-background-color: #9dcd08; -fx-border-color: #000000; -fx-border-width: 2px; -fx-font-size: 2em; ");
		PlayGame.setLayoutX(410);
		PlayGame.setLayoutY(185);
		
		PlayGame.setOnAction(PG->
		{
			//This is the pane that will display options to the buttons and the 
			
			//This will be the background for the user picks..
			Image userpicks = new Image ("UserScreen.png");
			ImageView user = new ImageView(userpicks);
			/* * * * * ******************** FIRST GRID * * * *************************************/
			grid.setHgap(20);
			grid.setVgap(10);
			grid.setLayoutX(60);
			grid.setLayoutY(100);
			
			int k = 1;
			for (int i = 0; i < 2; i++) 
			{
				for(int j = 0; j < 5; j++) 
				{
					Button button = new Button(String.valueOf(k));
					button.setStyle("-fx-background-color: #c7a0bd; "
							+ "-fx-border-color: #000000; "
							+ "-fx-border-width: 2px; "
							+ "-fx-font-size: 2em; ");
					
					button.setOnAction(GB1->
					{
						rounds = Integer.valueOf(button.getText());
						grid.setDisable(true);
						XLevel.setDisable(false);
					});
					
					k++;
					grid.add(button, j, i);
				}
				
			}
			/***************************************************************************************/
			//This section over here is setting up the button for the user to pick the level options
			XLevel = new GridPane();
			XLevel.setHgap(20);
			XLevel.setLayoutX(65);
			XLevel.setLayoutY(280);
			//This section is creating the buttons
			for(int i = 0; i < 3; i++) 
			{
				Button button = new Button(Levels[i]);
				button.setStyle("-fx-background-color: #9ac056; " //Setting the style for the buttons
						+ "-fx-border-color: #000000; "
						+ "-fx-border-width: 2px; "
						+ "-fx-font-size: 1.2em; ");
				
				button.setOnAction(GB2->
				{
					levelX = button.getText();
					OLevel.setDisable(false);
					
				});
				XLevel.add(button,i, 0); //adding button so the grid...
				XLevel.setDisable(true);
			}
			
			/***************************************************************************************/
			//This section over here is setting up the button for the user to pick the level options
			OLevel = new GridPane();
			OLevel.setHgap(20);
			OLevel.setLayoutX(65);
			OLevel.setLayoutY(330);
			//This section is creating the buttons
			for(int i = 0; i < 3; i++) 
			{
				Button button = new Button(Levels[i]);
				button.setStyle("-fx-background-color: #8da3e7; " //Setting the style for the buttons
						+ "-fx-border-color: #000000; "
						+ "-fx-border-width: 2px; "
						+ "-fx-font-size: 1.2em; ");
				
				button.setOnAction(GB3->
				{
					levelO = button.getText();
					StartGame.setDisable(false);
					
					
				});
				OLevel.add(button,i, 0); //adding button so the grid...
				OLevel.setDisable(true);
			}
			
			/***************************************************************************************/
			
			UserPick.getChildren().addAll(user, grid, XLevel,OLevel, StartGame);
			
			
			primaryStage.setScene(TheUserChoises);
			primaryStage.show();
			
		});
		
		//--------------------------------------------------------------------------------------------------
		//Creating the initial scene for the game.....
		//This is the creation of the first scene... Every Button will have a new scene...
		Pane introPane = new Pane(); //This is where I will be placing all the buttons and decor for the initial scene..
		
		//Adding an image background to the tic tac toe pane...
		Image TicTacToeIntro = new Image("TicTacToe2.png");
		ImageView Intro = new ImageView(TicTacToeIntro);
		
		
		//Adding elements to the introPane...
		introPane.getChildren().addAll(Intro,Instructions, PlayGame, Exit);
		
		//Adding the pane to the first intro scene of the game...
		introScene = new Scene(introPane, 596, 350);
		
		
		//---------------------------------------------------------------------------------------------------
		//This is the Welcome scene scene...
		
		Pane WelcomePane = new Pane();
		
		Image welcomeImage = new Image("WelcomeScene.png");
		ImageView welcomescene = new ImageView(welcomeImage);

		
		WelcomePane.getChildren().addAll(welcomescene);
		
		WelcomeScene = new Scene(WelcomePane, 517, 431);
		primaryStage.setScene(WelcomeScene);
		
		
		PauseTransition nextScene = new PauseTransition(Duration.seconds(2));
		nextScene.setOnFinished(WS->{
			primaryStage.setScene(introScene);
			primaryStage.show();
		});
		
		nextScene.play();
		primaryStage.show();

		
		//--------------------------------------------------------------------------------------------------
		
		
		
		
		
		
		

	}

}
