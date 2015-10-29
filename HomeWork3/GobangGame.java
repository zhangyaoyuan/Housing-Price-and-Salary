import java.io.BufferedReader;
import java.io.InputStreamReader;

class LinkInfo
{
	public boolean isLinkN;
	public int isLive[];		//0死链，1半活链，2活链([0]-[3]表横竖左斜右斜)
	public int linknum[];		//([0]死链，[1]半活链，[2]活链)
	LinkInfo()
	{
		isLinkN=false;
		isLive=new int[4];
		linknum=new int[3];
	}
	public String toString()
	{
		String r=new String("isLinkN="+isLinkN+";isLive[0~3]="+isLive[0]+isLive[1]+isLive[2]+isLive[3]+";linknum[0~2]="+linknum[0]+linknum[1]+linknum[2]);
		return r;
	}
	public int getLiveNum()
	{
		int lvn=0;
		for(int i=0;i<4;i++)
			if(isLive[i]==2) lvn++;
		return lvn;
	}
	public int getHLiveNum()
	{
		int lvn=0;
		for(int i=0;i<4;i++)
			if(isLive[i]==1) lvn++;
		return lvn;
	}
	public int getDeathNum()
	{
		int lvn=0;
		for(int i=0;i<4;i++)
			if(isLive[i]==0) lvn++;
		return lvn;
	}
} 
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
	 * 计算机随机下棋
	 */
	public int[] computerDo() {
		
		int posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		int posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		String[][] board = chessboard.getBoard();
		while (board[posX][posY] != "十") {
			posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
			posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		}
		
		//////////////////////////////////////////////////
		int[] result = {0, 0} ;
		
		LinkInfo linkinfo=new LinkInfo();
		int maxpriv=0;
/*		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
				if(isChessOn[i][j]==NONE)
				{
					int priv=0;
					priv=getPriv(i,j,side);
					if(priv>=maxpriv)
					{
						maxpriv=priv;
						result[0]=i;
						result[1]=j;					
					}
				}
			}//endfor
		}//endfor
*/		
		return result;
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
		String strVertical = null;
		String strHorizon = null;
		String strL2R = null;
		String strR2L = null;
		String[][] board = chessboard.getBoard();
		
		//Create Vertical String
		for(int v = posY-4; v<posY+4; v++)
		{
			if(v < 0 || v >= Chessboard.BOARD_SIZE)
				continue;
			strVertical += board[posX][v];
		}
		
		if(isWinInString(strVertical, ico))
			return true;
		//Create Horizontal String 
		for(int h = posX-4; h<posX+4; h++)
		{
			if(h<0 || h>=Chessboard.BOARD_SIZE-1)
				continue;
			strHorizon += board[h][posY];
		}
		if(isWinInString(strHorizon, ico))
			return true;
		
		//Create L2R String
		int lX, lY;
		for(lX=posX-4, lY=posY-4; lX<posX+4; lX++, lY++)
		{
			if(lX<0 || lX>=Chessboard.BOARD_SIZE || lY<0 || lY>=Chessboard.BOARD_SIZE)
				continue;
			strL2R += board[lX][lY];
		}
		if(isWinInString(strL2R, ico))
			return true;
		//Create R2L String
		int rX, rY;
		for(rX=posX+4, rY=posY+4; rX>posX-4; rX--, rY--)
		{
			if(rX<0 || rX>=Chessboard.BOARD_SIZE || rY<0 || rY>=Chessboard.BOARD_SIZE)
				continue;
			strR2L += board[rX][rY];
		}
		if(isWinInString(strR2L, ico))
			return true;
		
		return false;
	}
	
	//判断字符串 str中是否有5子相连
	public boolean isWinInString(String str, String ico)
	{
		String strTarget = null;
		for(int i=0; i<5; i++)
		{
			strTarget += ico;
		}
		
		return str.contains(strTarget);
	}
	public static void main(String[] args) throws Exception {

		GobangGame gb = new GobangGame(new Chessboard());
		gb.start();
	}
}
