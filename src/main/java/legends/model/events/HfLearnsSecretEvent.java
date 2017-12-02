package legends.model.events;

import legends.model.World;
import legends.model.events.basic.ArtifactRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("hf learns secret")
public class HfLearnsSecretEvent extends Event implements HfRelatedEvent, ArtifactRelatedEvent {
	@Xml("student,student_hfid")
	private int studentHfId = -1;
	@Xml("teacher,teacher_hfid")
	private int teacherHfId = -1;
	@Xml("artifact,artifact_id")
	private int artifactId = -1;
	@Xml(value = "interaction", track = true)
	private String interaction;
	@Xml(value = "secret_text", track = true)
	private String secretText;

	public int getStudentHfId() {
		return studentHfId;
	}

	public void setStudentHfId(int studentHfId) {
		this.studentHfId = studentHfId;
	}

	public int getTeacherHfId() {
		return teacherHfId;
	}

	public void setTeacherHfId(int teacherHfId) {
		this.teacherHfId = teacherHfId;
	}

	public int getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(int artifactId) {
		this.artifactId = artifactId;
	}

	public String getInteraction() {
		return interaction;
	}

	public void setInteraction(String interaction) {
		this.interaction = interaction;
	}

	public String getSecretText() {
		return secretText;
	}

	public void setSecretText(String secretText) {
		this.secretText = secretText;
	}

	@Override
	public boolean isRelatedToArtifact(int artifactId) {
		return this.artifactId == artifactId;
	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return studentHfId == hfId || teacherHfId == hfId;
	}

	@Override
	public String getShortDescription() {
		String student = World.getHistoricalFigure(studentHfId).getLink();
		String secret = "the secrets of life and death";
		if (!interaction.startsWith("SECRET_"))
			secret = interaction;
		if (secretText != null)
			secret = secretText.substring(1, secretText.length() - 1).split(":")[1];
		if (teacherHfId != -1)
			return World.getHistoricalFigure(teacherHfId).getLink() + " taught " + student + " " + secret;
		if (artifactId != -1)
			return student + " learned " + secret + " from " + World.getArtifact(artifactId).getLink();
		return student + " learns " + interaction;
	}

}
