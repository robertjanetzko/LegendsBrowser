package legends.model;

import java.util.LinkedList;
import java.util.List;

import legends.helper.EventHelper;
import legends.xml.annotation.Xml;

public class EntityHonor {
	@Xml("id")
	private int id;
	@Xml("name")
	private String name;
	@Xml("gives_precedence")
	private int givesPrecedence = -1; /// unused
	@Xml("required_skill")
	private String requiredSkill;
	@Xml("required_skill_ip_total")
	private int requiredSkillIpTotal = -1; /// unused
	@Xml("required_battles")
	private int requiredBattles = -1;
	@Xml("exempt_epid")
	private int exemptEpid = -1; /// unused
	@Xml("exempt_former_epid")
	private int exemptFormerEpid = -1; /// unused
	@Xml("required_years")
	private int requiredYears = -1;
	@Xml("required_kills")
	private int requiredKills = -1; /// TODO unused
	@Xml("requires_any_melee_or_ranged_skill")
	private boolean requiresAnyMeleeOrRangedSkill;
	@Xml("granted_to_everybody")
	private boolean grantedToEverybody; /// unused

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGivesPrecedence() {
		return givesPrecedence;
	}

	public void setGivesPrecedence(int givesPrecedence) {
		this.givesPrecedence = givesPrecedence;
	}

	public String getRequiredSkill() {
		return requiredSkill;
	}

	public void setRequiredSkill(String requiredSkill) {
		this.requiredSkill = requiredSkill;
	}

	public int getRequiredSkillIpTotal() {
		return requiredSkillIpTotal;
	}

	public void setRequiredSkillIpTotal(int requiredSkillIpTotal) {
		this.requiredSkillIpTotal = requiredSkillIpTotal;
	}

	@Override
	public String toString() {
		return name;
	}

	public Object getRequirement() {
		List<String> skills = new LinkedList<>();
		if (grantedToEverybody)
			skills.add("attaining sufficent skill with a weapon or technique");
		if (requiredSkill != null)
			skills.add("attaining enough skill with the " + requiredSkill.toLowerCase());
		if (requiredBattles == 1)
			skills.add("serving in combat");
		else if (requiredBattles > 1)
			skills.add(String.format("participating in %d battles", requiredBattles));
		if (requiredYears != -1)
			skills.add(String.format("%d years of membership", requiredYears));
		if (requiredKills != -1)
			skills.add("KILLS " + requiredKills);
		return " after " + skills.stream().collect(EventHelper.stringList());
	}

}
