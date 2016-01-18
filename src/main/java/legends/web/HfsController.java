package legends.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import legends.helper.EventHelper;
import legends.model.HistoricalFigure;
import legends.model.World;
import legends.web.basic.Controller;
import legends.web.basic.RequestMapping;

@Controller
public class HfsController {

	@RequestMapping("/hfs")
	public Template hfs(VelocityContext context) {
		boolean leader = context.containsKey("leader");
		boolean deity = context.containsKey("deity");
		boolean force = context.containsKey("force");
		boolean vampire = context.containsKey("vampire");
		boolean werebeast = context.containsKey("werebeast");
		boolean necromancer = context.containsKey("necromancer");
		boolean alive = context.containsKey("alive");
		boolean ghost = context.containsKey("ghost");
		boolean adventurer = context.containsKey("adventurer");

		if (leader || deity || force || vampire || werebeast || necromancer || alive || ghost || adventurer) {
			context.put("elements", World.getHistoricalFigures().stream().filter(hf -> {
				if (leader && !hf.isLeader())
					return false;
				if (deity && !hf.isDeity())
					return false;
				if (force && !hf.isForce())
					return false;
				if (vampire && !hf.isVampire())
					return false;
				if (werebeast && !hf.isWerebeast())
					return false;
				if (necromancer && !hf.isNecromancer())
					return false;
				if (alive && hf.getDeathYear() != -1)
					return false;
				if (ghost && !hf.isGhost())
					return false;
				if (adventurer && !hf.isAdventurer())
					return false;
				return true;
			}).collect(Collectors.toList()));
		} else {
			context.put("elements", World.getHistoricalFigures());
		}

		return Velocity.getTemplate("hfs.vm");
	}

	@RequestMapping("/hf/{id}")
	public Template hf(VelocityContext context, int id) {
		HistoricalFigure hf = World.getHistoricalFigure(id);
		HistoricalFigure.setContext(hf);

		context.put("hf", hf);
		context.put("family", new Family(hf, 3, 5));
		context.put("events", World.getHistoricalEvents().stream().filter(e -> EventHelper.related(hf, e))
				.collect(Collectors.toList()));

		return Velocity.getTemplate("hf.vm");
	}

	public class FamilyMember {
		HistoricalFigure hf;
		int generation;
		int distance;
		float x;
		boolean strongLink = false;

		FamilyMember father, mother, spouse;
		Set<FamilyMember> children = new HashSet<>();

		public FamilyMember(HistoricalFigure hf, int generation, int distance) {
			this.hf = hf;
			this.generation = generation;
			this.distance = distance;
		}

		public int getGeneration() {
			return generation;
		}

		public int getDistance() {
			return distance;
		}

		public HistoricalFigure getHf() {
			return hf;
		}

		public float getX() {
			return x;
		}

		public String getRelation() {
			if (generation == 0 && distance == 0) {
				return " ";
			}
			if (generation == -3 && distance == 3) {
				// grand parents
				if (hf.isFemale())
					return "great-grandmother";
				else
					return "great-grandfather";
			}
			if (generation == -2 && distance == 2) {
				// grand parents
				if (hf.isFemale())
					return "grandmother";
				else
					return "grandfather";
			}
			if (generation == -1 && distance == 1) {
				// parents
				if (hf.isFemale())
					return "mother";
				else
					return "father";
			}
			if (generation == -1 && distance == 3) {
				// parents
				if (hf.isFemale())
					return "aunt";
				else
					return "uncle";
			}
			if (generation == 0 && distance == 1) {
				// spouse
				if (hf.isFemale())
					return "wife";
				else
					return "husband";
			}
			if (generation == 0 && distance == 2) {
				// sibling
				if (hf.isFemale())
					return "sister";
				else
					return "brother";
			}
			if (generation == 0 && distance == 3) {
				if (hf.isFemale())
					return "sister-in-law";
				else
					return "brother-in-law";
			}
			if (generation == 0 && distance == 4) {
				// spouse
				return "cousin";
			}
			if (generation == 1 && distance == 1) {
				// child
				if (hf.isFemale())
					return "daughter";
				else
					return "son";
			}
			if (generation == 1 && distance == 2) {
				// child
				if (hf.isFemale())
					return "daughter-in-law";
				else
					return "son-in-law";
			}
			if (generation == 1 && distance == 3) {
				// child
				if (hf.isFemale())
					return "niece";
				else
					return "nephew";
			}
			if (generation == 2 && distance == 2) {
				// grand child
				if (hf.isFemale())
					return "granddaughter";
				else
					return "grandson";
			}
			if (generation == 3 && distance == 3) {
				// grand child
				if (hf.isFemale())
					return "great-granddaughter";
				else
					return "great-grandson";
			}

			return "";
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof FamilyMember)
				return hf.getId() == ((FamilyMember) obj).hf.getId();
			return false;
		}

		@Override
		public int hashCode() {
			return hf.getId();
		}
	}

	public class FamilyLink {
		String type;
		FamilyMember m1, m2;
		int x1, x2;

		public FamilyLink(String type, FamilyMember m1, FamilyMember m2) {
			this.type = type;
			if (m1.generation <= m2.generation) {
				this.m1 = m1;
				this.m2 = m2;
			} else {
				this.m1 = m1;
				this.m2 = m1;
			}
		}

		public String getType() {
			return type;
		}

		public FamilyMember getM1() {
			return m1;
		}

		public FamilyMember getM2() {
			return m2;
		}

		public float getX1() {
			if (m1.generation != m2.generation && m1.spouse != null) {
				return (m1.x + m1.spouse.x) / 2f;
			} else
				return m1.x;
		}

		public int getX2() {
			return x2;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof FamilyLink) {
				FamilyLink l = (FamilyLink) obj;
				return (l.m1.equals(m1) && l.m2.equals(m2)) || (l.m1.equals(m2) && l.m2.equals(m1));
			}
			return false;
		}

		@Override
		public int hashCode() {
			return Math.max(m1.hashCode(), m2.hashCode()) << 16 + Math.min(m1.hashCode(), m2.hashCode());
		}
	}

	public class Family {
		private int maxGenerations;
		private int maxDistance;
		private List<FamilyMember> members = new ArrayList<>();
		private Set<FamilyLink> links = new LinkedHashSet<>();

		public Family(HistoricalFigure hf, int maxGenerations, int maxDistance) {
			this.maxGenerations = maxGenerations;
			this.maxDistance = maxDistance;
			FamilyMember m = new FamilyMember(hf, 0, 0);
			m.strongLink = true;
			addMember(m);
			while (!queue.isEmpty()) {
				analyzeMember(queue.remove(0));
			}

			layout();
		}

		public void addMember(FamilyMember m) {
			if (members.contains(m))
				return;
			members.add(m);
			queue.add(m);
		}

		public void addMember(FamilyMember m, FamilyMember after) {
			if (members.contains(m))
				return;
			members.add(members.indexOf(after), m);
			if (queue.contains(after))
				queue.add(queue.indexOf(after), m);
			else
				queue.add(m);
		}

		List<FamilyMember> queue = new LinkedList<>();

		public void analyzeMember(FamilyMember m) {
			if (!m.strongLink)
				return;

			HistoricalFigure spouse = m.hf.getHfLink("spouse");
			if (spouse.getId() != -1) {
				FamilyMember m2 = get(spouse.getId(), new FamilyMember(spouse, m.generation, m.distance + 1));
				if (!m2.getRelation().equals("")) {
					m.spouse = m2;
					m2.spouse = m;
					links.add(new FamilyLink("spouse", m, m2));
					addMember(m2, m);
				}
			}

			// if (m.generation >= 0) {
			List<HistoricalFigure> children = m.hf.getHfLinks("child");
			for (HistoricalFigure c : children) {
				FamilyMember m2 = get(c.getId(), new FamilyMember(c, m.generation + 1, m.distance + 1));
				if (!m2.getRelation().equals("")) {
					m.children.add(m2);
					if (m.hf.getCaste().equals("FEMALE"))
						m2.mother = m;
					else
						m2.father = m;
					links.add(new FamilyLink("child", m, m2));
					m2.strongLink = true;
					addMember(m2);
				}
			}
			// }

			// if (m.generation <= 0) {
			HistoricalFigure father = m.hf.getHfLink("father");
			if (father.getId() != -1) {
				FamilyMember m2 = get(father.getId(), new FamilyMember(father, m.generation - 1, m.distance + 1));
				if (!m2.getRelation().equals("")) {
					m.father = m2;
					m2.children.add(m);
					links.add(new FamilyLink("father", m, m2));
					m2.strongLink = true;
					addMember(m2);
				}
			}

			HistoricalFigure mother = m.hf.getHfLink("mother");
			if (mother.getId() != -1) {
				FamilyMember m2 = get(mother.getId(), new FamilyMember(mother, m.generation - 1, m.distance + 1));
				if (!m2.getRelation().equals("")) {
					m.mother = m2;
					m2.children.add(m);
					links.add(new FamilyLink("mother", m, m2));
					m2.strongLink = true;
					addMember(m2);
				}
			}


			// }
		}

		private FamilyMember get(int id, FamilyMember defaultMember) {
			for (FamilyMember m : members) {
				if (m.getHf().getId() == id)
					return m;
			}
			return defaultMember;
		}

		private List<FamilyMember> getChildren(FamilyMember m) {
			return m.children.stream().filter(members::contains).collect(Collectors.toList());
		}

		private void layout() {
			// init
			for (int generation : getGenerations()) {
				int cnt = 0;
				for (FamilyMember m : getMembers(generation)) {
					m.x = cnt;
					cnt++;
				}
			}

			// move parents over children
			// boolean change;
			// do {
			// change = false;
			for (int i = getGenerations().size() - 1; i >= 0; i--) {
				int generation = getGenerations().get(i);
				float cnt = 0;
				for (FamilyMember m : getMembers(generation)) {
					List<FamilyMember> children = getChildren(m);
					if (children.size() > 0) {
						float min = (float) children.stream().mapToDouble(FamilyMember::getX).min().orElse(0);
						float max = (float) children.stream().mapToDouble(FamilyMember::getX).max().orElse(0);
						float center = (min + max) / 2f;
						if (m.spouse == null) {
//							if (center > m.x)
//								m.x = center;
						} else {
//							float curCenter = (m.x + m.spouse.x) / 2;
//							if (center > curCenter) {
//								float d = center - curCenter;
//								m.x += d;
//								m.spouse.x += d;
//							}
						}

					}
				}
			}
			//
			// } while (change);

			// move children under parents
			for (int generation : getGenerations()) {
				List<FamilyMember> gm = getMembers(generation);
				for (int i = gm.size() - 1; i >= 0; i--) {
					FamilyMember m = gm.get(i);
					List<FamilyMember> children = getChildren(m);
					if (children.size() > 0) {
						float center = m.x;
						if (m.spouse != null)
							center = (m.x + m.spouse.x) / 2f;
						for (int j = children.size() - 1; j >= 0; j--) {
							FamilyMember c = children.get(j);
							float newX = center - (children.size() - 1) / 2f + j;
							List<FamilyMember> gm2 = getMembers(c.generation);
							int gm2Idx = gm2.indexOf(c);
							if (newX > c.x && (gm2Idx == gm2.size() - 1 || gm2.get(gm2Idx + 1).x - 1 > newX))
								c.x = newX;
						}
					}
				}
			}

		}

		public List<Integer> getGenerations() {
			return members.stream().map(FamilyMember::getGeneration).distinct().sorted().collect(Collectors.toList());
		}

		public List<FamilyMember> getMembers(int generation) {
			return members.stream().filter(m -> m.getGeneration() == generation).collect(Collectors.toList());
		}

		public Set<FamilyLink> getLinks() {
			return links;
		}
		
		public float getMaxX() {
			return (float)members.stream().mapToDouble(FamilyMember::getX).max().orElse(0);
		}
	}

	@RequestMapping("/hffamily/{id}")
	public Template hffamily(VelocityContext context, int id) {
		HistoricalFigure hf = World.getHistoricalFigure(id);
		context.put("family", new Family(hf, 3, 3));
		return Velocity.getTemplate("hffamily.vm");
	}
}
