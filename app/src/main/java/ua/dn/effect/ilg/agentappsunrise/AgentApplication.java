package ua.dn.effect.ilg.agentappsunrise;

import android.app.Application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ua.dn.effect.ilg.agentappsunrise.data.config.AgentAppConfig;
import ua.dn.effect.ilg.agentappsunrise.data.exchenge.FtpConnection;
import ua.dn.effect.ilg.agentappsunrise.data.model.Check;
import ua.dn.effect.ilg.agentappsunrise.data.model.Client;
import ua.dn.effect.ilg.agentappsunrise.data.model.Notification;
import ua.dn.effect.ilg.agentappsunrise.data.model.Order;
import ua.dn.effect.ilg.agentappsunrise.data.model.PriceList;
import ua.dn.effect.ilg.agentappsunrise.data.model.Report;
import ua.dn.effect.ilg.agentappsunrise.data.model.SalesHistory;
import ua.dn.effect.ilg.agentappsunrise.data.model.TradeAgent;
import ua.dn.effect.ilg.agentappsunrise.util.ApplicationState;

/**
 * User: igrebeshkov
 * Date: 25.10.13
 * Time: 11:26
 */
public class AgentApplication extends Application {

    public static FtpConnection ftpConnection = new FtpConnection();

    public static String TEST_TEXT = "";

    public static boolean isDictionariesPresent = false;

    public static Order currentOrder = new Order();

    public static Report currentReport;

    public static List<Client> ClientsList;

    public static SalesHistory salesHistory;

    public static PriceList priceList;

    public static Date controlDate = new Date();

    public static TradeAgent tradeAgent;

    public static ArrayList<String> ordersFiles;

    public static AgentAppConfig config;

    public static List<Report> reportsList;

    public static List<Check> checkList;

    public static List<Notification> notificationsList;

    public static Check currentCheck;

    public static ApplicationState state = ApplicationState.NAN;
}
