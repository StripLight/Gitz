package org.gitz.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Class to model a list of command lines that is executed after a given Git command has run.
 */
@XmlRootElement
public class PostCommandTask {

    private String gitCommand;

    private List<PostCommandTaskEntry> taskEntries;

    @XmlElement(name = "command")
    public String getGitCommand() {
        return gitCommand;
    }

    public void setGitCommand(String gitCommand) {
        this.gitCommand = gitCommand;
    }

    @XmlElementWrapper(name = "postCommandTaskEntries")
    @XmlElement(name = "postCommandTaskEntry", type = PostCommandTaskEntry.class)
    public List<PostCommandTaskEntry> getTaskEntries() {
        return taskEntries;
    }

    public void setTaskEntries(List<PostCommandTaskEntry> taskEntries) {
        this.taskEntries = taskEntries;
    }

    public boolean matchesCommand(String command) {
        return getGitCommand().equalsIgnoreCase(command);
    }

	/**
	 * Load a run folder for all task entries
	 *
	 * @param folderPath	String
	 */
	public void loadRunFolder(String folderPath) {

        if (taskEntries == null) {
            return;
        }

        for (PostCommandTaskEntry entry : getTaskEntries()) {
            entry.setBaseRunFolder(folderPath);
        }
    }


}
