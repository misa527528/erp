package com.cqupt.mis.erp.service.forwardquarter;

public interface ForwardQuarterService {
	
	
	/**
	 * 进入下一周期前检测跳转状态	,用(int)forwardStatus来判断	<br>
	 * 0:用户已经破产										<br>
	 * 1:用户正在经营										<br>
	 * 2:是年初但未完成广告费投放和订单选取确认						<br>
	 * 3:游戏已经结束										<br>
	 * @param userUnique
	 *
	 * @return
	 */
	int ForwardStatus(String userUnique);
	
	/**
	 * 判断用户是否gameOver	<br>
	 * true:	GameOver	<br>
	 * false:	继续玩呗！	<br>
	 * @param userUnique
	 * @author lx
	 * @return
	 */
	boolean isGameOver(String userUnique);
	
	/**
	 * 判断资产负债-用户是否破产		<br>
	 * ture：破产					<br>
	 * false：未破产					<br>
	 * @param userUnique
	 * @author lx
	 * @return
	 */
	boolean isBalancesheetOut(String userUnique);
	
	
	/**	
	 * 判断用户是否还有cash		<br>
	 * true:	有				<br>
	 * false：	木有				<br>	
	 * @param userUnique
	 * @author lx
	 * @return
	 */
	boolean isHaveCash(String userUnique);
	
	/**
	 * 破产清算,设置破产用户在后面每年年初选订单的状态
	 * @author lx
	 * @param userUnique
	 */
	void bankruptcy(String userUnique);
	
	/**
	 * 如果用户已经破产，我们需要在以后的每年年初设置该破产用户的选单状态为1(已经选单)，
	 * 不去影响其他人的游戏进度.
	 * @param userUnique
	 * @author lx
	 */
	void insertDataToAdststusofuser(String userUnique, int D);
	
	/**
	 * 判断用户是否已经结束比赛，如果结束，进行相应的操作，如果没有，则什么也不做！
	 * @param userUnique
	 * @author lx
	 */
	boolean updateFinishedGame(String userUnique);
	
	/**
	 * 如果所有用户里最小的currentPeriod都比GameGroup里的currentPeriod大，则更新游戏组的currentPeriod
	 * @author lx
	 */
	void updateGroupCurrentPeriod(String groupName);
	
	
	
}
