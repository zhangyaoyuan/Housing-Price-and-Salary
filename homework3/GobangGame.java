import java.io.BufferedReader;
import java.io.InputStreamReader;

 
public class GobangGame {
	// 定义达到赢条件的棋子数目
	private final int WIN_COUNT = 5;
	// 定义用户输入的X坐标
	private int posX = 0;
	// 定义用户输入的X坐标
	private int posY = 0;
	// 定义棋盘
	private Chessboard chessboard;

	/**
	 * 空构造器
	 */
	public GobangGame() {
	}

	/**
	 * 构造器，初始化棋盘和棋子属性
	 * 
	 * @param chessboard
	 *            棋盘类
	 */
	public GobangGame(Chessboard chessboard) {
		this.chessboard = chessboard;
	}

	/**
	 * 检查输入是否合法。
	 * 
	 * @param inputStr
	 *            由控制台输入的字符串。
	 * @return 字符串合法返回true,反则返回false。
	 */
	public boolean isValid(String inputStr) {
		// 将用户输入的字符串以逗号(,)作为分隔，分隔成两个字符串
		String[] posStrArr = inputStr.split(",");
		try {
			posX = Integer.parseInt(posStrArr[0]) - 1;
			posY = Integer.parseInt(posStrArr[1]) - 1;
		} catch (NumberFormatException e) {
			chessboard.printBoard();
			System.out.println("请以(数字,数字)的格式输入：");
			return false;
		}
		// 检查输入数值是否在范围之内
		if (posX < 0 || posX >= Chessboard.BOARD_SIZE || posY < 0
				|| posY >= Chessboard.BOARD_SIZE) {
			chessboard.printBoard();
			System.out.println("X与Y坐标只能大于等于1,与小于等于" + Chessboard.BOARD_SIZE
					+ ",请重新输入：");
			return false;
		}
		// 检查输入的位置是否已经有棋子
		String[][] board = chessboard.getBoard();
		if (board[posX][posY] != "十") {
			chessboard.printBoard();
			System.out.println("此位置已经有棋子，请重新输入：");
			return false;
		}
		return true;
	}

	/**
	 * 开始下棋
	 */
	public void start() throws Exception {
		// true为游戏结束
		boolean isOver = false;
		chessboard.initBoard();
		chessboard.printBoard();
		// 获取键盘的输入
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = null;
		// br.readLine:每当键盘输入一行内容按回车键，则输入的内容被br读取到
		while ((inputStr = br.readLine()) != null) {
			isOver = false;
			if (!isValid(inputStr)) {
				// 如果不合法，要求重新输入，再继续
				continue;
			}
			// 把对应的数组元素赋为"●"
			String chessman = Chessman.BLACK.getChessman();
			chessboard.setBoard(posX, posY, chessman);
			// 判断用户是否赢了
			if (isWon(posX, posY, chessman)) {
				isOver = true;

			} else {
				// 计算机随机选择位置坐标
				int[] computerPosArr = computerDo();
				chessman = Chessman.WHITE.getChessman();
				chessboard.setBoard(computerPosArr[0], computerPosArr[1],
						chessman);
				// 判断计算机是否赢了
				if (isWon(computerPosArr[0], computerPosArr[1], chessman)) {
					isOver = true;
				}
			}
			// 如果产生胜者，询问用户是否继续游戏
			if (isOver) {
				// 如果继续，重新初始化棋盘，继续游戏
				if (isReplay(chessman)) {
					chessboard.initBoard();
					chessboard.printBoard();
					continue;
				}
				// 如果不继续，退出程序
				break;
			}
			chessboard.printBoard();
			System.out.println("请输入您下棋的坐标，应以x,y的格式输入：");
		}
	}

	/**
	 * 是否重新开始下棋。
	 * 
	 * @param chessman
	 *            "●"为用户，"○"为计算机。
	 * @return 开始返回true，反则返回false。
	 */
	public boolean isReplay(String chessman) throws Exception {
		chessboard.printBoard();
		String message = chessman.equals(Chessman.BLACK.getChessman()) ? "恭喜您，您赢了，"
				: "很遗憾，您输了，";
		System.out.println(message + "再下一局？(y/n)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if (br.readLine().equals("y")) {
			// 开始新一局
			return true;
		}
		return false;

	}

	/**
	 * 计算机AI下棋
	 * 总体思想,计算所有位置的权值，然后选择权值最大的位置下棋
	 */
	public int[] computerDo() {
		int[][] weight = new int[Chessboard.BOARD_SIZE][Chessboard.BOARD_SIZE];
		//记录电脑下棋的位置
		int[] pos = new int[2];
		int max = 0;
		String[][] board = chessboard.getBoard();
		//计算所有位置的权值，已经存在棋子的位置，权值为-1
		for(int i=0; i<Chessboard.BOARD_SIZE; i++){
			for(int j=0; j<Chessboard.BOARD_SIZE; j++){
				if(board[i][j] != "十")weight[i][j] = -1;
				//计算权值
				else weight[i][j] = weightCal(i,j);
			}
		}
		//遍历寻找权值最大的位置
		for(int m=0; m<Chessboard.BOARD_SIZE; m++){
			for(int n=0; n<Chessboard.BOARD_SIZE; n++){
				if(weight[m][n] > max){
					max = weight[m][n];
					pos[0] = m;
					pos[1] = n;
				}
			}
		}
		return pos;
	}

	/**
	 * 计算该位置权值。
	 * 
	 * @param x  所要计算位置的x坐标
	 * @param y  所要计算位置的y坐标
	 * @return 该位置的权值。
	 */
	public int weightCal(int x,int y){
		int weight = 0;   //该位置的总权值
		int[] weights = new int[4];    //保存该位置四个方向的权值
		int w1 = 100000;    //电脑四连子，权值为w1
		int w2 = 50000;	    //玩家四连子，权值为w2
		int w3 = 10000;	    //电脑三连子
		int w4 = 5000;      //玩家三连子
		int w5 = 1000;      //电脑二连子
		int w6 = 500;       //玩家二连子
		int w7 = 100;	    //电脑一连子
		int w8 = 50;	    //玩家一连子
		String white = Chessman.WHITE.getChessman();
		String black = Chessman.BLACK.getChessman();
		//计算该位置四个方向的电脑连子数
		weights[0] = Cal1(x,y,white);
		weights[1] = Cal2(x,y,white);
		weights[2] = Cal3(x,y,white);
		weights[3] = Cal4(x,y,white);
		//累计权值
		for(int i=0; i<weights.length; i++){
			if(weights[i] == 4)weight += w1;
			else if(weights[i] == 3)weight += w3;
			else if(weights[i] == 2)weight += w5;
			else if(weights[i] == 1)weight += w7;
		}
		//计算该位置四个方向的玩家连子数
		weights[0] = Cal1(x,y,black);
		weights[1] = Cal2(x,y,black);
		weights[2] = Cal3(x,y,black);
		weights[3] = Cal4(x,y,black);
		//累计权值
		for(int i=0; i<weights.length; i++){
			if(weights[i] == 4)weight += w2;
			else if(weights[i] == 3)weight += w4;
			else if(weights[i] == 2)weight += w6;
			else if(weights[i] == 1)weight += w8;
		}
		return weight;
	}
	
	
	/**
	 * 计算该位置横向连子数。
	 * 
	 * @param x  所要计算位置的x坐标
	 * @param y  所要计算位置的y坐标
	 * @return 该位置的对应颜色连子数。
	 */
	public int Cal1(int x,int y,String ico){
		int[] border = new int[4];
		border = border(x,y);
		int count1 = 0;
		int count2 = 0;
		int i = x-1;
		String[][] board = chessboard.getBoard();
		//从该位置向左找
		while( i >= border[0] && board[i][y] == ico){
			++count1;
			--i;
		}
		//从该位置向右找
		i = x+1;
		while(i <= border[2] && board[i][y] == ico){
			++count2;
			++i;
		}
		//返回两个方向中连子数较大者
		return count1 > count2 ? count1 : count2;
	}
	
	/**
	 * 计算该位置纵向连子数。
	 * 
	 * @param x  所要计算位置的x坐标
	 * @param y  所要计算位置的y坐标
	 * @return 该位置的对应颜色连子数。
	 */
	public int Cal2(int x,int y,String ico){
		int[] border = new int[4];
		border = border(x,y);
		int count1 = 0;
		int count2 = 0;
		int i = y-1;
		String[][] board = chessboard.getBoard();
		//从该位置向上找
		while( i >= border[1] && board[x][i] == ico){
			++count1;
			--i;
		}
		//从该位置向下找
		i = y+1;
		while(i <= border[3] && board[x][i] == ico){
			++count2;
			++i;
		}
		//返回两个方向中连子数较大者
		return count1 > count2 ? count1 : count2;
	}
	
	/**
	 * 计算该位置斜下方向连子数。
	 * 
	 * @param x  所要计算位置的x坐标
	 * @param y  所要计算位置的y坐标
	 * @return 该位置的对应颜色连子数。
	 */
	public int Cal3(int x,int y,String ico){
		int[] border = new int[4];
		border = border(x,y);
		int count1 = 0;
		int count2 = 0;
		int i = x-1;
		int j = y-1;
		String[][] board = chessboard.getBoard();
		//从该位置沿左上方向找
		while( i >= border[0] && j >= border[1] && board[i][j] == ico){
			++count1;
			--i;
			--j;
		}
		//从该位置沿右下方向找
		i = x+1;
		j = y+1;
		while(i <= border[2] && j <= border[3] && board[i][j] == ico){
			++count2;
			++i;
			++j;
		}
		//返回两个方向中连子数较大者
		return count1 > count2 ? count1 : count2;
	}
	
	/**
	 * 计算该位置斜上方向连子数。
	 * 
	 * @param x  所要计算位置的x坐标
	 * @param y  所要计算位置的y坐标
	 * @return 该位置的对应颜色连子数。
	 */
	public int Cal4(int x,int y,String ico){
		int[] border = new int[4];
		border = border(x,y);
		int count1 = 0;
		int count2 = 0;
		int i = x-1;
		int j = y+1;
		String[][] board = chessboard.getBoard();
		//从该位置沿右上方向找
		while( i >= border[0] && j <= border[3] && board[i][j] == ico){
			++count1;
			--i;
			++j;
		}
		//从该位置沿左下方向找
		i = x+1;
		j = y-1;
		while(i <= border[2] && j >= border[1] && board[i][j] == ico){
			++count2;
			++i;
			--j;
		}
		//返回两个方向中连子数较大者
		return count1 > count2 ? count1 : count2;
	}
	
	public int[] border(int x,int y){
		int[] border = new int[4];
		border[0] = 0;
		border[1] = 0;
		border[2] = chessboard.BOARD_SIZE-1;
		border[3] = chessboard.BOARD_SIZE-1; 
		int size = posX - WIN_COUNT + 1;
		border[0] = size < 0 ? 0 : size;
		size = posY - WIN_COUNT + 1;
		border[1] = size < 0 ? 0 : size;
		size = posX + WIN_COUNT - 1;
		border[2] = size > chessboard.BOARD_SIZE-1 ? chessboard.BOARD_SIZE-1 : size;
		size = posY + WIN_COUNT - 1;
		border[3] = size > chessboard.BOARD_SIZE-1 ? chessboard.BOARD_SIZE-1 : size;
		return border;
		
	}
	/**
	 * 判断输赢
	 * 
	 * @param posX
	 *            棋子的X坐标。
	 * @param posY
	 *            棋子的Y坐标
	 * @param ico
	 *            棋子类型
	 * @return 如果有五颗相邻棋子连成一条直接，返回真，否则相反。
	 */
	public boolean isWon(int posX, int posY, String ico) { 
		//以落子点为中心，向四个方向扫描，start_x_chess记录落子点左半部分
		//第一个非落子颜色的棋子x坐标，end_x_chess记录右半部分第一个非落子颜色的棋子x坐标
		int[] border =new int[4];
		border = border(posX,posY);
		
		int start_x_chess = posX;
		int start_y_chess = posY;
		int end_x_chess = posX;
		int end_y_chess = posY;
		boolean is_win = false;
		
		String[][] board = chessboard.getBoard();
		//以落子点为中心横向扫描，记录两边第一个非落子颜色的棋子的x坐标。
		while(start_x_chess >= border[0] && board[start_x_chess][posY] == ico)--start_x_chess;
		while(end_x_chess <= border[2] && board[end_x_chess][posY] == ico)++end_x_chess;
		//因为记录的是非落子颜色的棋子x坐标，所以相减为6才能说明获胜。
		if(end_x_chess - start_x_chess == 6)is_win = true;
		//以落子点为中心纵向扫描，记录两边第一个非落子颜色的棋子的y坐标
		start_x_chess = posX;
		start_y_chess = posY;
		end_x_chess = posX;
		end_y_chess = posY;
		if(!is_win){
			while(start_y_chess >= border[1] && board[posX][start_y_chess] == ico)--start_y_chess;
			while(end_y_chess <= border[3] && board[posX][end_y_chess] ==ico)++end_y_chess;
			if(end_y_chess - start_y_chess == 6)is_win = true;
		}
		//斜下扫描
		start_x_chess = posX;
		start_y_chess = posY;
		end_x_chess = posX;
		end_y_chess = posY;
		if(!is_win){
			while(start_y_chess >= border[1] && start_x_chess >= border[0] && board[start_x_chess][start_y_chess] == ico){
				--start_y_chess;
				--start_x_chess;
			}
			while(end_y_chess <= border[3] && end_x_chess <= border[2] && board[end_x_chess][end_y_chess] ==ico){
				++end_y_chess;
				++end_x_chess;
			}
			if(end_y_chess - start_y_chess == 6)is_win = true;
		}
		//斜上扫描
		start_x_chess = posX;
		start_y_chess = posY;
		end_x_chess = posX;
		end_y_chess = posY;
		if(!is_win){
			while(start_y_chess <= border[3] && start_x_chess >= border[0] && board[start_x_chess][start_y_chess] == ico){
				++start_y_chess;
				--start_x_chess;
			}
			while(end_y_chess >= border[1] && end_x_chess <= border[2] && board[end_x_chess][end_y_chess] ==ico){
				--end_y_chess;
				++end_x_chess;
			}
			if(end_x_chess - start_x_chess == 6)is_win = true;
		}
		return is_win;
	}

	public static void main(String[] args) throws Exception {

		GobangGame gb = new GobangGame(new Chessboard());
		gb.start();
	}
}
