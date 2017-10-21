import org.json.JSONArray;
import org.json.JSONObject;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.PingEvent;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot extends ListenerAdapter {

	//Mods usernames must be lowercase
	private List<String> activeUsers = new ArrayList<>();

	public Bot() {
		activeUsers.add("*");
	}


	/**
	 * PircBotx will return the exact message sent and not the raw line
	 */
	@Override
	public void onGenericMessage(GenericMessageEvent event) throws Exception {
		String message = event.getMessage();
		String userName = event.getUser().getNick();
		boolean allowedForEveryone = activeUsers.contains("*");
		if (allowedForEveryone)
			Main.writeToSerialPort(message);
		else {
			for (String s : activeUsers) {
				if (s.equalsIgnoreCase(userName)) {
					Main.writeToSerialPort(message);
					break;
				}
			}
		}
	}

	/**
	 * The command will always be the first part of the message
	 * We can split the string into parts by spaces to get each word
	 * The first word if it starts with our command notifier "!" will get returned
	 * Otherwise it will return null
	 */
	private String getCommandFromMessage(String message) {
		String[] msgParts = message.split(" ");
		if (msgParts[0].startsWith("!")) {
			return msgParts[0];
		} else {
			return null;
		}
	}

	/**
	 * We MUST respond to this or else we will get kicked
	 */
	@Override
	public void onPing(PingEvent event) throws Exception {
		Main.bot.sendRaw().rawLineNow(String.format("PONG %s\r\n", event.getPingValue()));
	}

	private void sendMessage(String message) {
		Main.bot.sendIRC().message("#" + Main.CHANNEL, message);
	}
}