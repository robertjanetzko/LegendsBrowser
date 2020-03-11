package legends.model;

import java.util.ArrayList;
import java.util.List;

import legends.Application;
import legends.model.basic.AbstractObject;
import legends.model.events.WrittenContentComposedEvent;
import legends.model.events.basic.Event;
import legends.model.events.basic.EventLocation;
import legends.model.events.basic.Filters;
import legends.xml.annotation.Xml;

public class WrittenContent extends AbstractObject {
	@Xml("title")
	private String title;
	@Xml("page_start")
	private int pageStart = -1;
	@Xml("page_end")
	private int pageEnd = -1;
	@Xml("type,form")
	private String type;
	@Xml(value = "reference", elementClass = Reference.class, multiple = true)
	private List<Reference> references = new ArrayList<>();
	@Xml(value = "style", elementClass = String.class, multiple = true)
	private List<String> styles = new ArrayList<>();
	@Xml("author,author_hfid")
	private int authorHfId = -1;
	@Xml("form_id")
	private int formId = -1;
	@Xml("author_roll")
	private int authorRoll = -1;

	private List<Event> events = new ArrayList<>();

	public String getTitle() {
		if (title == null || title.equals(""))
			return "untitled " + type;
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPageStart() {
		return pageStart;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	public int getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAuthorHfId() {
		return authorHfId;
	}

	public void setAuthorHfId(int authorHfId) {
		this.authorHfId = authorHfId;
	}

	public int getFormId() {
		return formId;
	}

	public void setFormId(int formId) {
		this.formId = formId;
	}
	
	public int getAuthorRoll() {
		return authorRoll;
	}

	public void setAuthorRoll(int authorRoll) {
		this.authorRoll = authorRoll;
	}

	public List<Reference> getReferences() {
		return references;
	}

	public List<String> getStyles() {
		return styles;
	}

	public String getUrl() {
		return Application.getSubUri() + "/writtencontent/" + id;
	}

	public String getLink() {
		if (id == -1)
			return "<i>UNKNOWN WRITTEN CONTENT</i>";
		return "<a href=\"" + getUrl() + "\" class=\"writtencontent\">" + getTitle() + "</a>";
	}

	public List<Event> getEvents() {
		return events;
	}

	public Site getAuthoredIn() {
		return events.stream().collect(Filters.filterEvent(WrittenContentComposedEvent.class))
				.map(WrittenContentComposedEvent::getLocation).map(EventLocation::getSiteId).map(World::getSite)
				.findFirst().orElse(World.UNKNOWN_SITE);
	}

	public String getAuthoredOn() {
		return events.stream().collect(Filters.filterEvent(WrittenContentComposedEvent.class)).map(Event::getDate)
				.findFirst().orElse("");
	}
	
	public String getQualityString() {
		String result = "";
		List<String> informationForms = new ArrayList<>();
		informationForms.add("atlas");
		informationForms.add("star chart");
		informationForms.add("encyclopedia");
		informationForms.add("chronicle");
		informationForms.add("dictionary");
		informationForms.add("treatise on technological evolution");
		
		if (informationForms.contains(type)) {
			if (authorRoll < 10) {
				result = "Overall, this is a terrible compilation of information.";
			} else if (authorRoll >= 10 && authorRoll < 20) {
				result = "Overall, the information is not compiled very well.";
			} else if (authorRoll >= 20 && authorRoll < 30) {
				result = "Overall, this is a good source of information.";
			} else if (authorRoll >= 30 && authorRoll < 40) {
				result = "Overall, this is a great source of information.";
			} else if (authorRoll >= 40 && authorRoll < 50) {
				result = "Overall, this is a splendid source of information.";
			} else if (authorRoll >= 50) {
				result = "Overall, the information is compiled masterfully.";
			}
		} else if (type.contains("poem")) {
			if (authorRoll < 10) {
				result = "Overall, the poetry is very, very bad.";
			} else if (authorRoll >= 10 && authorRoll < 20) {
				result = "Overall, the poetry is not awful, but not very good either.";
			} else if (authorRoll >= 20 && authorRoll < 30) {
				result = "Overall, the poetry is passable.";
			} else if (authorRoll >= 30 && authorRoll < 40) {
				result = "Overall, the poetry is great.";
			} else if (authorRoll >= 40 && authorRoll < 50) {
				result = "Overall, the poetry is inspired.";
			} else if (authorRoll >= 50) {
				result = "Overall, the poetry is masterful.";
			}
		} else if (type.contains("musical composition")) {
			if (authorRoll < 10) {
				result = "Overall, the composition is atrocious.";
			} else if (authorRoll >= 10 && authorRoll < 20) {
				result = "Overall, the composition is not awful, but not very good either.";
			} else if (authorRoll >= 20 && authorRoll < 30) {
				result = "Overall, the composition is passable.";
			} else if (authorRoll >= 30 && authorRoll < 40) {
				result = "Overall, the composition is great.";
			} else if (authorRoll >= 40 && authorRoll < 50) {
				result = "Overall, the composition is inspired.";
			} else if (authorRoll >= 50) {
				result = "Overall, the composition is masterful.";
			}
		} else if (type.contains("choreography")) {
			if (authorRoll < 10) {
				result = "Overall, the choreography is just terrible.";
			} else if (authorRoll >= 10 && authorRoll < 20) {
				result = "Overall, the choreography is not awful, but not very good either.";
			} else if (authorRoll >= 20 && authorRoll < 30) {
				result = "Overall, the choreography is passable.";
			} else if (authorRoll >= 30 && authorRoll < 40) {
				result = "Overall, the choreography is great.";
			} else if (authorRoll >= 40 && authorRoll < 50) {
				result = "Overall, the choreography is inspired.";
			} else if (authorRoll >= 50) {
				result = "Overall, the choreography is masterful.";
			}
		} else {
			if (authorRoll < 10) {
				result = "Overall, the prose is amateurish at best.";
			} else if (authorRoll >= 10 && authorRoll < 20) {
				result = "Overall, the prose is not awful, but not very good either.";
			} else if (authorRoll >= 20 && authorRoll < 30) {
				result = "Overall, the prose is passable.";
			} else if (authorRoll >= 30 && authorRoll < 40) {
				result = "Overall, the prose is great.";
			} else if (authorRoll >= 40 && authorRoll < 50) {
				result = "Overall, the prose is spendid.";
			} else if (authorRoll >= 50) {
				result = "Overall, the prose is masterful.";
			}
		}
		
		return result;
	}
	
	public String getStyleString() {
		if (styles.isEmpty()) {
			return "";
		}
		
		String result = "The writing ";
		List<String> styleString = new ArrayList<String>();
		int authorWeight = 50;
		
		for (String style : styles) {
			String[] splitter = style.split(":");
			int strength = Integer.parseInt(splitter[1]);
			
			if  (style.contains("meandering")) {
				if (authorRoll>authorWeight) {
					if (strength > 2) {
						styleString.add("rapidly shifts between topics");
					} else if (strength == 2) {
						styleString.add("moves quickly from subject to subject");
					} else {
						styleString.add("takes frequent interesting tangents");
					}
				} else {
					if (strength > 2) {
						styleString.add("hopelessly meanders");
					} else if (strength == 2) {
						styleString.add("meanders somewhat");
					} else {
						styleString.add("gets off track from time to time");
					}
				}
			}
			
			if  (style.contains("cheerful")) {
				if (strength > 2) {
					styleString.add("bubbles over with cheerfulness");
				} else if (strength == 2) {
					styleString.add("is quite cheerful");
				} else {
					styleString.add("is fairly cheerful");
				}
			}
			
			if  (style.contains("melancholy")) {
				if (strength > 2) {
					styleString.add("overflows with sadness");
				} else if (strength == 2) {
					styleString.add("is depressing");
				} else {
					styleString.add("has a touch of melancholy");
				}
			}
			
			if  (style.contains("mechanical")) {
				if (authorRoll>authorWeight) {
					if (strength > 2) {
						styleString.add("is thoroughly organized");
					} else if (strength == 2) {
						styleString.add("is clearly organized");
					} else {
						styleString.add("is organized");
					}
				} else {
					if (strength > 2) {
						styleString.add("is thoroughly mechanical");
					} else if (strength == 2) {
						styleString.add("is very rigid");
					} else {
						styleString.add("is a bit stiff");
					}
				}
			}
			
			if  (style.contains("serious")) {
				if (strength > 2) {
					styleString.add("is completely serious");
				} else if (strength == 2) {
					styleString.add("has a very serious tone");
				} else {
					styleString.add("is reasonably serious");
				}
			}
			
			if  (style.contains("disjointed")) {
				if (authorRoll>authorWeight) {
					if (strength > 2) {
						styleString.add("artfully flashes through seemingly disjoint topics");
					} else if (strength == 2) {
						styleString.add("breaks sharply between topics with regularity");
					} else {
						styleString.add("has some particularly crisp changes in topic");
					}
				} else {
					if (strength > 2) {
						styleString.add("is utterly disjointed");
					} else if (strength == 2) {
						styleString.add("skips around a lot");
					} else {
						styleString.add("occasionally changes topic abruptly");
					}
				}
			}
			
			if  (style.contains("florid")) {
				if (authorRoll>authorWeight) {
					if (strength > 2) {
						styleString.add("uses only artful words and phrases selected with care");
					} else if (strength == 2) {
						styleString.add("is throughout ornate and refined");
					} else {
						styleString.add("has rare but particularly exquisite turns of phrase");
					}
				} else {
					if (strength > 2) {
						styleString.add("is excessively ornate");
					} else if (strength == 2) {
						styleString.add("is quite florid");
					} else {
						styleString.add("is somewhat showy");
					}
				}
			}
			
			if  (style.contains("forceful")) {
				if (strength > 2) {
					styleString.add("drives forward relentlessly");
				} else if (strength == 2) {
					styleString.add("is full of force");
				} else {
					styleString.add("is forceful");
				}
			}
			
			if  (style.contains("humorous")) {
				if (authorRoll>authorWeight) {
					if (strength > 2) {
						styleString.add("is a comedy treasure trove");
					} else if (strength == 2) {
						styleString.add("is quite humorous");
					} else {
						styleString.add("has a touch of humor");
					}
				} else {
					if (strength > 2) {
						styleString.add("is littered with terrible jokes inartfully told");
					} else if (strength == 2) {
						styleString.add("has poorly-executed humor");
					} else {
						styleString.add("tries and fails to be funny at times");
					}
				}
			}
			
			if  (style.contains("puerile")) {
				if (authorRoll>authorWeight) {
					if (strength > 2) {
						styleString.add("is artfully suffused with what would otherwise be childish and immature material");
					} else if (strength == 2) {
						styleString.add("contains a deft use throughout of what might be considered immature material");
					} else {
						styleString.add("manages an artful inclusion here and there of childish material");
					}
				} else {
					if (strength > 2) {
						styleString.add("is absolutely puerile");
					} else if (strength == 2) {
						styleString.add("is very childish");
					} else {
						styleString.add("is a bit immature");
					}
				}
			}
			
			if  (style.contains("self indulgent")) {
				if (authorRoll>authorWeight) {
					if (strength > 2) {
						styleString.add("indulges the author's every whim");
					} else if (strength == 2) {
						styleString.add("artfully indulges the author's fancies");
					} else {
						styleString.add("contains some asides related to the author's preferences");
					}
				} else {
					if (strength > 2) {
						styleString.add("is stunningly self-indulgent");
					} else if (strength == 2) {
						styleString.add("is quite self-indulgent");
					} else {
						styleString.add("is somewhat self-indulgent");
					}
				}
			}
			
			if  (style.contains("compassionate")) {
				if (authorRoll>authorWeight) {
					if (strength > 2) {
						styleString.add("could only have been produced by a merciful spirit");
					} else if (strength == 2) {
						styleString.add("is quite touching");
					} else {
						styleString.add("shows a hint of tenderness");
					}
				} else {
					if (strength > 2) {
						styleString.add("is clear evidence of a compassionate being");
					} else if (strength == 2) {
						styleString.add("is very compassionate");
					} else {
						styleString.add("gives a feeling of compassion here and there");
					}
				}
			}
			
			if  (style.contains("vicious")) {
				if (strength > 2) {
					styleString.add("is as vicious as can be");
				} else if (strength == 2) {
					styleString.add("is quite cruel");
				} else {
					styleString.add("has a hint of viciousness to it");
				}
			}
			
			if  (style.contains("concise")) {
				if (strength > 2) {
					styleString.add("is a monument to concision");
				} else if (strength == 2) {
					styleString.add("is very concise");
				} else {
					styleString.add("is fairly crisp");
				}
			}
			
			if  (style.contains("sardonic")) {
				if (strength > 2) {
					styleString.add("is a pit of bitter scorn");
				} else if (strength == 2) {
					styleString.add("is quite mocking in tone");
				} else {
					styleString.add("contains the occasional biting remark");
				}
			}
			
			if  (style.contains("witty")) {
				if (strength > 2) {
					styleString.add("is a reservoir of amusingly clever quips");
				} else if (strength == 2) {
					styleString.add("is refreshingly witty");
				} else {
					styleString.add("has its moments of cleverness");
				}
			}
			
			if  (style.contains("rant")) {
				if (authorRoll>authorWeight) {
					if (strength > 2) {
						styleString.add("is an awe-inspiring wild rant");
					} else if (strength == 2) {
						styleString.add("careens forward as a rant");
					} else {
						styleString.add("tumbles out almost as a rant at times");
					}
				} else {
					if (strength > 2) {
						styleString.add("is a completely unhinged rant");
					} else if (strength == 2) {
						styleString.add("is a rant more or less");
					} else {
						styleString.add("descends to a rant in places");
					}
				}
			}
		}
		
		return result + String.join(" and it ", styleString) + ".";
	}

}
