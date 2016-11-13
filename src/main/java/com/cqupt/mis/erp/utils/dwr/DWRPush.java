package com.cqupt.mis.erp.utils.dwr;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;

import java.util.Collection;

/**
 * 
 * DWRPush
 * 跟这个类都是一个神奇的东西, 如果你看到这里的话就去简单的看看DWR使用, 
 * 简单一句话就是可以在html 页面中使用javascript 代码来调用后台的service 代码. 
 * 同时也可以做到,想具体的浏览器做定向推送, 但是我做的都是简单的推送. 
 * 最后补一句.  这个玩意对浏览器的要求较高, 同时不能最大化的保证这个连接已经连上, 所以出现各种bug. 如果用户会简单的刷新就可以避免这些问题. 
 * 
 * @author heinz_ho
 */
public class DWRPush {
	/**
	 * pushMessageWithFilter 这个方法是有标识推送. 
	 * @param javascriptMethodName
	 * @param message
	 * @param FilterPage 
	 *void
	 * 
	 */
	public static void pushMessageWithFilter(final String javascriptMethodName,
			final String message, final String FilterPage) {

		try {

			// 执行推送
			Browser.withAllSessionsFiltered(new ScriptSessionFilter() {
				public boolean match(ScriptSession session) {
					if (session.getAttribute("tag") == null)
						return false;
					else
						return (session.getAttribute("tag")).equals(FilterPage);
				}
			}, new Runnable() {
				@Override
				public void run() {
					ScriptBuffer script = new ScriptBuffer();
					// 设置要调用的 js及参数
					script.appendCall(javascriptMethodName, message);
					// 得到所有ScriptSession
					Collection<ScriptSession> sessions = Browser
							.getTargetSessions();
					// 遍历每一个ScriptSession
					for (ScriptSession scriptSession : sessions) {
						scriptSession.addScript(script);
					}
				}

			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * pushMessage  这个方法会想所有连上的做广播 .
	 * @param javascriptMethodName
	 * @param message 
	 *void
	 * @exception 
	 * @since  1.0.0
	 */
	public static void pushMessage(final String javascriptMethodName,
			final String message) {
		Runnable run = new Runnable() {
			private ScriptBuffer script = new ScriptBuffer();

			public void run() {
				// 设置要调用的 js及参数
				script.appendCall(javascriptMethodName, message);
				// 得到所有ScriptSession
				Collection<ScriptSession> sessions = DWRScriptSessionListener
						.getScriptSessions();
				// 遍历每一个ScriptSession
				for (ScriptSession scriptSession : sessions) {
					scriptSession.addScript(script);
				}
			}
		};
		// 执行推送
		Browser.withAllSessions(run);
		// Browser.withCurrentPage(run);
	}

	/**
	 * refresh 这个也是一个广播的方法.广播的页面中应该都需要有js 的 refresh 方法, 要不然会跳出乱七八糟的报错. 
	 *void
	 * @exception 
	 * @since  1.0.0
	 */
	public static void refresh() {
		Runnable run = new Runnable() {
			private ScriptBuffer script = new ScriptBuffer();

			public void run() {
				// 设置要调用的 js及参数
				script.appendCall("refresh");
				// 得到所有ScriptSession
				Collection<ScriptSession> sessions = DWRScriptSessionListener
						.getScriptSessions();
				// 遍历每一个ScriptSession
				for (ScriptSession scriptSession : sessions) {
					scriptSession.addScript(script);
				}
			}
		};
		// 执行推送
		Browser.withAllSessions(run);
		// Browser.withCurrentPage(run);
	}
}
