package legends.model.events;

import legends.model.World;
import legends.model.events.basic.ArtifactRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;

public class HfLearnsSecretEvent extends Event implements HfRelatedEvent, ArtifactRelatedEvent {
	private int studentHfId = -1;
	private int teacherHfId = -1;
	private int artifactId = -1;
	private String interaction;

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

	@Override
	public boolean setProperty(String property, String value) {
		switch (property) {

		case "student_hfid":
			setStudentHfId(Integer.parseInt(value));
			break;
		case "teacher_hfid":
			setTeacherHfId(Integer.parseInt(value));
			break;
		case "artifact_id":
			setArtifactId(Integer.parseInt(value));
			break;
		case "interaction":
			setInteraction(value);
			break;

		default:
			return super.setProperty(property, value);
		}
		return true;
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
		if (teacherHfId != -1)
			return World.getHistoricalFigure(teacherHfId).getLink() + " taught " + student + " " + secret;
		if (artifactId != -1)
			return student + " learned " + secret + " from " + World.getArtifact(artifactId).getLink();
		return student + " learns " + interaction;
	}

}
