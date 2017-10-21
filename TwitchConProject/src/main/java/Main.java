import jssc.SerialPort;
import jssc.SerialPortException;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;


public class Main {

	public static final String BOTNAME = "Siglerbot";
	public static final String OAUTH = "oauth:r5a3st750vqm5z6blg2zveul9y6fk9";
	public static final String CHANNEL = "97waterpolo";

	public static PircBotX bot;
    private static SerialPort serialPort;
	public static void main(String[] args) throws Exception {
        initSerialPort(); //Initiate the Serial Port for Adruino

		System.out.println("Program starting");
		System.out.println(JSONParser.fromUrl("https://api.twitch.tv/helix/users?login=dansgaming"));
		Configuration config = new Configuration.Builder()
				.setName(BOTNAME)
				.setServer("irc.chat.twitch.tv", 6667)
				.setServerPassword(OAUTH)
				.addListener(new Bot())
				.addAutoJoinChannel("#" + CHANNEL)
				.buildConfiguration();

		bot = new PircBotX(config);
		System.out.println("Starting bot");
		bot.startBot();
		System.out.println("The Bot has started!");

        serialPort.closePort();
	}

	public static void writeToSerialPort(String s){
        try {
            serialPort.writeBytes(s.getBytes());//Write data to port
        }catch(SerialPortException e){e.printStackTrace();}
    }

	private static void initSerialPort(){
		serialPort = new SerialPort("COM3");
		try {
			serialPort.openPort();//Open serial port
			serialPort.setParams(SerialPort.BAUDRATE_9600,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);//Set params. Also you can set params by this string: serialPort.setParams(9600, 8, 1, 0);
		}
		catch (SerialPortException ex) {
			System.out.println(ex);
		}
	}
}