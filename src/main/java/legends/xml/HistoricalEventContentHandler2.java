package legends.xml;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.HistoricalEvent;
import legends.xml.handlers.ElementContentHandler;

public class HistoricalEventContentHandler2 extends ElementContentHandler<HistoricalEvent> {

	HistoricalEvent event = new HistoricalEvent();

	public HistoricalEventContentHandler2(String name, XMLReader xmlReader) {
		super(name, xmlReader);
		setHandledValues("id", "year", "seconds72", "type", "subtype", "hfid", "hfid_target", "state", "civ_id",
				"site_civ_id", "site_id", "subregion_id", "feature_layer_id", "coords", "builder_hfid", "structure_id",
				"artifact_id", "unit_id", "hist_figure_id", "agreement_id", "entity_id", "group_hfid", "return",
				"occasion_id", "schedule_id", "winner_hfid", "competitor_hfid", "group_1_hfid", "group_2_hfid",
				"slayer_hfid", "slayer_race", "slayer_caste", "slayer_item_id", "slayer_shooter_item_id", "cause",
				"attacker_hfid", "attacker_civ_id", "defender_civ_id", "attacker_general_hfid", "defender_general_hfid",
				"woundee_hfid", "wounder_hfid", "wc_id", "reason", "reason_id", "circumstance", "circumstance_id",
				"target_hfid", "snatcher_hfid", "knowledge", "first", "changee_hfid", "changer_hfid", "old_race",
				"old_caste", "new_race", "new_caste", "secret_goal", "dispute", "entity_id_1", "entity_id_2",
				"site_id_1", "site_id_2","body_state","building_id");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "id":
			event.setId(Integer.parseInt(value));
			break;
		case "year":
			event.setYear(Integer.parseInt(value));
			break;
		case "seconds72":
			event.setSeconds72(Integer.parseInt(value));
			break;
		case "type":
			event.setType(value);
			break;
		case "subtype":
			event.setSubtype(value);
			break;
		case "hfid":
			event.setHfId(Integer.parseInt(value));
			break;
		case "hfid_target":
			event.setHfIdTarget(Integer.parseInt(value));
			break;
		case "state":
			event.setState(value);
			break;
		case "civ_id":
			event.setCivId(Integer.parseInt(value));
			break;
		case "site_civ_id":
			event.setSiteCivId(Integer.parseInt(value));
			break;
		case "site_id":
			event.setSiteId(Integer.parseInt(value));
			break;
		case "subregion_id":
			event.setSubregionId(Integer.parseInt(value));
			break;
		case "feature_layer_id":
			event.setFeatureLayerId(Integer.parseInt(value));
			break;
		case "coords":
			String[] coords = value.split(",");
			event.setX(Integer.parseInt(coords[0]));
			event.setY(Integer.parseInt(coords[1]));
			break;
		case "builder_hfid":
			event.setBuilderHfId(Integer.parseInt(value));
			break;
		case "structure_id":
			event.setStructureId(Integer.parseInt(value));
			break;
		case "artifact_id":
			event.setArtifactId(Integer.parseInt(value));
			break;
		case "unit_id":
			event.setUnitId(Integer.parseInt(value));
			break;
		case "hist_figure_id":
			event.setHistFigureId(Integer.parseInt(value));
			break;
		case "agreement_id":
			event.setAgreementId(Integer.parseInt(value));
			break;
		case "entity_id":
			event.setEntityId(Integer.parseInt(value));
			break;
		case "group_1_hfid":
			event.setGroup1HfId(Integer.parseInt(value));
			break;
		case "group_2_hfid":
			event.getGroup2HfIds().add(Integer.parseInt(value));
			break;
		case "group_hfid":
			event.setGroupHfId(Integer.parseInt(value));
			break;
		case "return":
			event.setReturn(true);
			break;
		case "occasion_id":
			event.setOccasionId(Integer.parseInt(value));
			break;
		case "schedule_id":
			event.setScheduleId(Integer.parseInt(value));
			break;
		case "winner_hfid":
			event.setWinnerHfId(Integer.parseInt(value));
			break;
		case "competitor_hfid":
			event.getCompetitorHfIds().add(Integer.parseInt(value));
			break;
		case "slayer_hfid":
			event.setSlayerHfId(Integer.parseInt(value));
			break;
		case "slayer_race":
			event.setSlayerRace(value);
			break;
		case "slayer_caste":
			event.setSlayerCaste(value);
			break;
		case "slayer_item_id":
			event.setSlayerItemId(Integer.parseInt(value));
			break;
		case "slayer_shooter_item_id":
			event.setSlayerShooterItemId(Integer.parseInt(value));
			break;
		case "cause":
			event.setCause(value);
			break;
		case "attacker_hfid":
			event.setAttackerHfId(Integer.parseInt(value));
			break;
		case "attacker_civ_id":
			event.setAttackerCivId(Integer.parseInt(value));
			break;
		case "defender_civ_id":
			event.setDefenderCivId(Integer.parseInt(value));
			break;
		case "attacker_general_hfid":
			event.setAttackerGeneralHfId(Integer.parseInt(value));
			break;
		case "defender_general_hfid":
			event.setDefenderGeneralHfId(Integer.parseInt(value));
			break;
		case "woundee_hfid":
			event.setWoundeeHfId(Integer.parseInt(value));
			break;
		case "wounder_hfid":
			event.setWounderHfId(Integer.parseInt(value));
			break;
		case "wc_id":
			event.setWcId(Integer.parseInt(value));
			break;
		case "reason":
			event.setReason(value);
			break;
		case "reason_id":
			event.setReasonId(Integer.parseInt(value));
			break;
		case "circumstance":
			event.setCircumstance(value);
			break;
		case "circumstance_id":
			event.setCircumstanceId(Integer.parseInt(value));
			break;
		case "target_hfid":
			event.setTargetHfId(Integer.parseInt(value));
			break;
		case "snatcher_hfid":
			event.setSnatcherHfId(Integer.parseInt(value));
			break;
		case "knowledge":
			event.setKnowledge(value);
			break;
		case "first":
			event.setFirst(true);
			break;
		case "changee_hfid":
			event.setChangeeHfId(Integer.parseInt(value));
			break;
		case "changer_hfid":
			event.setChangerHfId(Integer.parseInt(value));
			break;
		case "old_race":
			event.setOldRace(value);
			break;
		case "old_caste":
			event.setOldCaste(value);
			break;
		case "new_race":
			event.setNewRace(value);
			break;
		case "new_caste":
			event.setNewCaste(value);
			break;
		case "secret_goal":
			event.setSecretGoal(value);
			break;
		case "dispute":
			event.setDispute(value);
			break;
		case "entity_id_1":
			event.setEntityId1(Integer.parseInt(value));
			break;
		case "entity_id_2":
			event.setEntityId2(Integer.parseInt(value));
			break;
		case "site_id_1":
			event.setSiteId1(Integer.parseInt(value));
			break;
		case "site_id_2":
			event.setSiteId2(Integer.parseInt(value));
			break;
		case "body_state":
			event.setBodyState(value);
			break;
		case "building_id":
			event.setBuildingId(Integer.parseInt(value));
			break;
		default:
			super.endElement(uri, localName, qName);
			break;
		}
	}

	@Override
	public HistoricalEvent getElement() {
		HistoricalEvent a = event;
		event = new HistoricalEvent();
		return a;
	}

}
