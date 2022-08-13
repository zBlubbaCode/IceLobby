package de.zblubba.icelobby.util;

import de.zblubba.icelobby.IceLobby;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Updater {

    public enum ResultUpdate {
        AVAILABLE, UP_TO_DATE, ERROR
    }

    private long ressourceID;
    private String currentVersion;
    private String latestVersion;
    private ResultUpdate resultUpdate;

    public Updater(long ressourceID) {
        this.ressourceID = ressourceID;
        this.currentVersion = IceLobby.getInstance().getDescription().getVersion();
    }

    private void setResultUpdate(ResultUpdate resultUpdate) {
        this.resultUpdate = resultUpdate;
    }

    public void checkLatestPluginVersion() {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("https://api.spigotmc.org/legacy/update.php?ressource" + ressourceID).openConnection();
            latestVersion = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream())).readLine();

            if(latestVersion == null) {
                setResultUpdate(ResultUpdate.ERROR);
                return;
            }
        } catch (IOException exception) {
            setResultUpdate(ResultUpdate.ERROR);
        }
    }

    public void compareVersion() {
        if(latestVersion == null) {
            setResultUpdate(ResultUpdate.ERROR);
            return;
        }
        long currentVersionCompact = Long.parseLong(currentVersion.replace(".", ""));
        long latestVersionCompact = Long.parseLong(latestVersion.replace(".", ""));

        if(currentVersionCompact == latestVersionCompact) {
            setResultUpdate(ResultUpdate.UP_TO_DATE);
            return;
        }
        setResultUpdate(ResultUpdate.AVAILABLE);
    }

    public void runCheck() {
        IceLobby.getInstance().getLogger().info("Looking for an update on spigotmc.org");

        checkLatestPluginVersion();
        compareVersion();

        switch(resultUpdate) {
            case AVAILABLE:
                IceLobby.getInstance().getLogger().info("A new version of the plugin was found. Please install it on spigotmc.org!");
                IceLobby.isUpdateAvailable = true;
                break;
            case UP_TO_DATE:
                IceLobby.getInstance().getLogger().info("This plugin is up to date :D");
                IceLobby.isUpdateAvailable = false;
                break;
            case ERROR:
                IceLobby.getInstance().getLogger().info("Connection lost to spigotmc.org...");
                IceLobby.isUpdateAvailable = false;
                break;
            default:
                IceLobby.getInstance().getLogger().info("Connection lost to spigotmc.org....");
                IceLobby.isUpdateAvailable = false;
                break;
        }
    }
}
