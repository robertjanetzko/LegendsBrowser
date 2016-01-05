package legends.xml;

import java.util.HashSet;
import java.util.Set;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legends.model.World;
import legends.model.events.AddHfEntityLinkEvent;
import legends.model.events.AddHfHfLinkEvent;
import legends.model.events.AddHfSiteLinkEvent;
import legends.model.events.AgreementFormedEvent;
import legends.model.events.ArtFormCreatedEvent;
import legends.model.events.ArtifactEvent;
import legends.model.events.ArtifactLostEvent;
import legends.model.events.ArtifactPosessedEvent;
import legends.model.events.AttackedSiteEvent;
import legends.model.events.BodyAbusedEvent;
import legends.model.events.ChangeHfBodyStateEvent;
import legends.model.events.ChangeHfJobEvent;
import legends.model.events.ChangeHfStateEvent;
import legends.model.events.ChangedCreatureTypeEvent;
import legends.model.events.CompetitionEvent;
import legends.model.events.CreateEntityPositionEvent;
import legends.model.events.CreatedSiteEvent;
import legends.model.events.CreatedStructureEvent;
import legends.model.events.CreatedWorldConstructionEvent;
import legends.model.events.CreatureDevouredEvent;
import legends.model.events.DestroyedSiteEvent;
import legends.model.events.EntityCreatedEvent;
import legends.model.events.EntityLawEvent;
import legends.model.events.EntityPrimaryCriminalsEvent;
import legends.model.events.EntityReloacateEvent;
import legends.model.events.FieldBattleEvent;
import legends.model.events.HfAbductedEvent;
import legends.model.events.HfAttackedSiteEvent;
import legends.model.events.HfConfrontedEvent;
import legends.model.events.HfDestroyedSiteEvent;
import legends.model.events.HfDiedEvent;
import legends.model.events.HfDoesInteractionEvent;
import legends.model.events.HfGainsSecretGoalEvent;
import legends.model.events.HfLearnsSecretEvent;
import legends.model.events.HfNewPetEvent;
import legends.model.events.HfProfanedStructureEvent;
import legends.model.events.HfRelationshipDeniedEvent;
import legends.model.events.HfReunionEvent;
import legends.model.events.HfSimpleBattleEvent;
import legends.model.events.HfTravelEvent;
import legends.model.events.HfWoundedEvent;
import legends.model.events.ItemStolenEvent;
import legends.model.events.KnowledgeDiscoveredEvent;
import legends.model.events.NewSiteLeaderEvent;
import legends.model.events.PeaceEvent;
import legends.model.events.PerformanceEvent;
import legends.model.events.PlunderedSiteEvent;
import legends.model.events.RazedStructureEvent;
import legends.model.events.ReclaimSiteEvent;
import legends.model.events.RegionpopIncorporatedIntoEntityEvent;
import legends.model.events.RemoveHfEntityLinkEvent;
import legends.model.events.RemoveHfSiteLinkEvent;
import legends.model.events.ReplacedStructureEvent;
import legends.model.events.SiteDisputeEvent;
import legends.model.events.SiteRetiredEvent;
import legends.model.events.SiteTakenOverEvent;
import legends.model.events.WrittenContentComposedEvent;
import legends.model.events.basic.Event;
import legends.xml.handlers.ElementContentHandler;

public class HistoricalEventContentHandler extends ElementContentHandler<Event> {

	private String id, year, seconds;
	private Event event;

	private static Set<String> unknownTypes = new HashSet<>();

	public HistoricalEventContentHandler(String name, XMLReader xmlReader) {
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
				"site_id_1", "site_id_2", "body_state", "building_id");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (localName) {
		case "id":
			id = value;
			break;
		case "year":
			year = value;
			break;
		case "seconds72":
			seconds = value;
			break;
		case "type":
			if (World.isPlusMode()) {
				event = World.getHistoricalEvent(Integer.parseInt(id));
				if (event == null) {
					System.out.println("unknown event " + id+" "+value);
					unknownTypes.add(value);
				}
			} else {
				event = createEvent(value);

				if (event != null) {
					event.setId(Integer.parseInt(id));
					event.setYear(Integer.parseInt(year));
					event.setSeconds(Integer.parseInt(seconds));
					event.setType(value);
				}
			}
			break;

		default:
			if (event == null || !event.setProperty(localName, value)) {
				if (!localName.equals(getName()))
					System.out.println(
							"unkown list element tag: " + localName + " = " + value + " ? " + this + " " + event);

				super.endElement(uri, localName, qName);
			}
			break;
		}
	}

	private Event createEvent(String type) {
		switch (type) {
		case "add hf entity link":
			return new AddHfEntityLinkEvent();
		case "add hf hf link":
			return new AddHfHfLinkEvent();
		case "add hf site link":
			return new AddHfSiteLinkEvent();
		case "agreement formed":
			return new AgreementFormedEvent();
		case "artifact created":
		case "artifact stored":
			return new ArtifactEvent();
		case "artifact lost":
			return new ArtifactLostEvent();
		case "artifact possessed":
			return new ArtifactPosessedEvent();
		case "attacked site":
			return new AttackedSiteEvent();
		case "body abused":
			return new BodyAbusedEvent();
		case "change hf body state":
			return new ChangeHfBodyStateEvent();
		case "change hf job":
			return new ChangeHfJobEvent();
		case "change hf state":
			return new ChangeHfStateEvent();
		case "changed creature type":
			return new ChangedCreatureTypeEvent();
		case "competition":
			return new CompetitionEvent();
		case "created structure":
			return new CreatedStructureEvent();
		case "created world construction":
			return new CreatedWorldConstructionEvent();
		case "creature devoured":
			return new CreatureDevouredEvent();
		case "entity created":
			return new EntityCreatedEvent();
		case "entity law":
			return new EntityLawEvent();
		case "field battle":
			return new FieldBattleEvent();
		case "entity primary criminals":
			return new EntityPrimaryCriminalsEvent();
		case "entity relocate":
			return new EntityReloacateEvent();
		case "hf abducted":
			return new HfAbductedEvent();
		case "hf attacked site":
			return new HfAttackedSiteEvent();
		case "hf destroyed site":
			return new HfDestroyedSiteEvent();
		case "hf confronted":
			return new HfConfrontedEvent();
		case "hf died":
			return new HfDiedEvent();
		case "hf does interaction":
			return new HfDoesInteractionEvent();
		case "hf gains secret goal":
			return new HfGainsSecretGoalEvent();
		case "hf learns secret":
			return new HfLearnsSecretEvent();
		case "hf new pet":
			return new HfNewPetEvent();
		case "hf simple battle event":
			return new HfSimpleBattleEvent();
		case "hf relationship denied":
			return new HfRelationshipDeniedEvent();
		case "hf reunion":
			return new HfReunionEvent();
		case "hf profaned structure":
			return new HfProfanedStructureEvent();
		case "hf travel":
			return new HfTravelEvent();
		case "hf wounded":
			return new HfWoundedEvent();
		case "item stolen":
			return new ItemStolenEvent();
		case "created site":
			return new CreatedSiteEvent();
		case "destroyed site":
			return new DestroyedSiteEvent();
		case "knowledge discovered":
			return new KnowledgeDiscoveredEvent();
		case "new site leader":
			return new NewSiteLeaderEvent();
		case "peace rejected":
		case "peace accepted":
			return new PeaceEvent();
		case "plundered site":
			return new PlunderedSiteEvent();
		case "razed structure":
			return new RazedStructureEvent();
		case "reclaim site":
			return new ReclaimSiteEvent();
		case "regionpop incorporated into entity":
			return new RegionpopIncorporatedIntoEntityEvent();
		case "remove hf entity link":
			return new RemoveHfEntityLinkEvent();
		case "remove hf site link":
			return new RemoveHfSiteLinkEvent();
		case "replaced structure":
			return new ReplacedStructureEvent();
		case "written content composed":
			return new WrittenContentComposedEvent();
		case "site dispute":
			return new SiteDisputeEvent();
		case "site retired":
			return new SiteRetiredEvent();
		case "site taken over":
			return new SiteTakenOverEvent();
		case "musical form created":
		case "poetic form created":
		case "dance form created":
			return new ArtFormCreatedEvent();
		case "performance":
		case "ceremony":
		case "procession":
			return new PerformanceEvent();
		case "create entity position":
			return new CreateEntityPositionEvent();

		default:
			if (!unknownTypes.contains(type)) {
				System.err.println("unknown event type: " + type);
				unknownTypes.add(type);
			}

			return null;
		}
	}

	@Override
	public Event getElement() {
		Event e = event;
		event = null;
		return e;
	}
	
	public static void printUnknownTypes() {
		if(unknownTypes.size()>0)
			System.out.println("unknown event types: "+unknownTypes);
	}

}