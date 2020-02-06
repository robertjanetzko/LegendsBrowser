package legends.model.events;

import java.util.ArrayList;
import java.util.List;

import legends.helper.EventHelper;
import legends.model.World;
import legends.model.events.basic.EntityRelatedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.HfRelatedEvent;
import legends.xml.annotation.Xml;
import legends.xml.annotation.XmlSubtype;

@XmlSubtype("hf convicted")
public class HfConvictedEvent extends Event implements EntityRelatedEvent, HfRelatedEvent {
	@Xml("convicted_hfid")
	private int convictedHfId = -1;
	@Xml("convicter_enid")
	private int convicterEnId = -1;
	@Xml("target_hfid")
private int targetHfId = -1;
	@Xml(value = "crime", track = true)
	private String crime;
	@Xml(value = "prison_months", track = true)
	private int prisonMonths = -1;
	@Xml("death_penalty")
	private boolean deathPenalty;
	@Xml("exiled")
	private boolean exiled;
	@Xml("surveiled_contact")
	private boolean surveiledContact;
	@Xml("surveiled_convicted")
	private boolean surveiledConvicted;
	@Xml("surveiled_coconspirator")
	private boolean surveiledCoconspirator;
	@Xml("surveiled_target")
	private boolean surveiledTarget;
	@Xml("convict_is_contact")
	private boolean convictIsContact;
	@Xml("held_firm_in_interrogation")
	private boolean heldFirmInInterrogation;
	@Xml("did_not_reveal_all_in_interrogation")
	private boolean didNotRevealAllInInterrogation;
	@Xml("wrongful_conviction")
	private boolean wrongfulConviction;
	@Xml("contact_hfid")
	private int contactHfId = -1;
	@Xml(value = "implicated_hfid", elementClass = Integer.class, multiple = true)
	private List<Integer> implicatedHfIds = new ArrayList<>();
	@Xml("corrupt_convicter_hfid")
	private int corruptConvicterHfId = -1;
	@Xml("plotter_hfid")
	private int plotterHfId = -1;
	@Xml("coconspirator_hfid")
	private int coconspiratorHfId = -1;
	@Xml("fooled_hfid")
	private int fooledHfId = -1;
	@Xml("framer_hfid")
	private int framerHfId = -1;
	@Xml("confessed_after_apb_arrest_enid")
	private int confessedAfterApbArrestEnId = -1;
	@Xml("interrogator_hfid")
	private int interrogatorHfId = -1;

	@Override
	public boolean isRelatedToEntity(int entityId) {
		return convicterEnId == entityId;

	}

	@Override
	public boolean isRelatedToHf(int hfId) {
		return convictedHfId == hfId || contactHfId == hfId || corruptConvicterHfId == hfId || plotterHfId == hfId
				|| coconspiratorHfId == hfId || fooledHfId == hfId || framerHfId == hfId || targetHfId == hfId
				|| implicatedHfIds.contains(hfId);
	}

	public int getConvictedHfId() {
		return convictedHfId;
	}

	public void setConvictedHfId(int convictedHfId) {
		this.convictedHfId = convictedHfId;
	}

	public int getConvicterEnId() {
		return convicterEnId;
	}

	public void setConvicterEnId(int convicterEnId) {
		this.convicterEnId = convicterEnId;
	}

	public String getCrime() {
		return crime;
	}

	public void setCrime(String crime) {
		this.crime = crime;
	}

	public int getPrisonMonths() {
		return prisonMonths;
	}

	public void setPrisonMonths(int prisonMonths) {
		this.prisonMonths = prisonMonths;
	}

	public boolean isDeathPenalty() {
		return deathPenalty;
	}

	public void setDeathPenalty(boolean deathPenalty) {
		this.deathPenalty = deathPenalty;
	}

	public boolean isExiled() {
		return exiled;
	}

	public void setExiled(boolean exiled) {
		this.exiled = exiled;
	}

	public boolean isSurveiledContact() {
		return surveiledContact;
	}

	public void setSurveiledContact(boolean surveiledContact) {
		this.surveiledContact = surveiledContact;
	}

	public boolean isSurveiledConvicted() {
		return surveiledConvicted;
	}

	public void setSurveiledConvicted(boolean surveiledConvicted) {
		this.surveiledConvicted = surveiledConvicted;
	}

	public boolean isSurveiledCoconspirator() {
		return surveiledCoconspirator;
	}

	public void setSurveiledCoconspirator(boolean surveiledCoconspirator) {
		this.surveiledCoconspirator = surveiledCoconspirator;
	}

	public boolean isConvictIsContact() {
		return convictIsContact;
	}

	public void setConvictIsContact(boolean convictIsContact) {
		this.convictIsContact = convictIsContact;
	}

	public boolean isHeldFirmInInterrogation() {
		return heldFirmInInterrogation;
	}

	public void setHeldFirmInInterrogation(boolean heldFirmInInterrogation) {
		this.heldFirmInInterrogation = heldFirmInInterrogation;
	}

	public boolean isDidNotRevealAllInInterrogation() {
		return didNotRevealAllInInterrogation;
	}

	public void setDidNotRevealAllInInterrogation(boolean didNotRevealAllInInterrogation) {
		this.didNotRevealAllInInterrogation = didNotRevealAllInInterrogation;
	}

	public boolean isWrongfulConviction() {
		return wrongfulConviction;
	}

	public void setWrongfulConviction(boolean wrongfulConviction) {
		this.wrongfulConviction = wrongfulConviction;
	}

	public int getContactHfId() {
		return contactHfId;
	}

	public void setContactHfId(int contactHfId) {
		this.contactHfId = contactHfId;
	}

	public List<Integer> getImplicatedHfIds() {
		return implicatedHfIds;
	}

	public void setImplicatedHfIds(List<Integer> implicatedHfIds) {
		this.implicatedHfIds = implicatedHfIds;
	}

	public int getCorruptConvicterHfId() {
		return corruptConvicterHfId;
	}

	public void setCorruptConvicterHfId(int corruptConvicterHfId) {
		this.corruptConvicterHfId = corruptConvicterHfId;
	}

	public int getPlotterHfId() {
		return plotterHfId;
	}

	public void setPlotterHfId(int plotterHfId) {
		this.plotterHfId = plotterHfId;
	}

	public int getCoconspiratorHfId() {
		return coconspiratorHfId;
	}

	public void setCoconspiratorHfId(int coconspiratorHfId) {
		this.coconspiratorHfId = coconspiratorHfId;
	}

	public int getFooledHfId() {
		return fooledHfId;
	}

	public void setFooledHfId(int fooledHfId) {
		this.fooledHfId = fooledHfId;
	}

	public int getFramerHfId() {
		return framerHfId;
	}

	public void setFramerHfId(int framerHfId) {
		this.framerHfId = framerHfId;
	}

	@Override
	public String getShortDescription() {
		String convicted = World.getHistoricalFigure(convictedHfId).getLink();
		String convicter = World.getEntity(convicterEnId).getLink();
		String penalty = "";
		if (deathPenalty)
			penalty = " and sentenced to death";
		else if (exiled)
			penalty = " and exiled";
		else if (prisonMonths != -1)
			penalty = String.format(" and imprisoned for a term of %d years", prisonMonths / 12);
		String surveil = "";
		if (surveiledConvicted)
			surveil = "due to ongoing surveillance as the plot unfolded, ";
		if (surveiledContact)
			surveil = String.format("due to ongoing surveillance on the contact %s as the plot unfolded, ",
					World.getHistoricalFigure(contactHfId).getLink());
		if (surveiledCoconspirator)
			surveil = String.format("due to ongoing surveillance on a coconspiratior, %s, as the plot unfolded, ",
					World.getHistoricalFigure(coconspiratorHfId).getLink());
		if (confessedAfterApbArrestEnId != -1)
			surveil = "after being recognized and arrested, ";
		if (surveiledTarget)
			surveil = String.format("due to ongoing surveillance on the target %s as the plot unfolded, ", World.getHistoricalFigure(targetHfId).getShortLink());

		String goBetween = convictIsContact ? "as a go-between in a conspiracy to commit" : "of";
		String fool = "";
		if (fooledHfId != -1)
			fool = String.format(" after %s fooled %s with fabricated evidence%s ",
					World.getHistoricalFigure(framerHfId).getLink(), World.getHistoricalFigure(fooledHfId).getLink(),
					plotterHfId != -1
							? String.format(" at the behest of %s", World.getHistoricalFigure(plotterHfId).getLink())
							: "");
		if (corruptConvicterHfId != -1)
			fool = String.format(" and the corrupt %s through the machinations of %s ",
					World.getHistoricalFigure(corruptConvicterHfId).getShortLink(),
					World.getHistoricalFigure(plotterHfId).getLink());
		String interrogation = "";
		if (heldFirmInInterrogation)
			interrogation = String.format(". %s revealed nothing during interrogation",
					World.getHistoricalFigure(convictedHfId).getShortLink());
		else if (!implicatedHfIds.isEmpty())
			interrogation = String.format(". %s implicated %s during interrogation%s",
					World.getHistoricalFigure(convictedHfId).getShortLink(),
					implicatedHfIds.stream().map(World::getHistoricalFigure).collect(EventHelper.hfList()),
					didNotRevealAllInInterrogation ? " but did not reveal eaverything" : "");

		return String.format("%s%s %swas %sconvicted %s %s by %s%s%s%s", surveil, convicted,
				confessedAfterApbArrestEnId != -1 ? "confessed and " : "", wrongfulConviction ? "wrongfully " : "",
				goBetween, crime, convicter, fool, penalty, interrogation);
	}

}
