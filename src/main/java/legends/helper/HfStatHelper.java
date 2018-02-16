package legends.helper;

import java.util.HashMap;

import legends.model.HistoricalFigure;
import legends.model.World;
import legends.model.events.HfDiedEvent;

public class HfStatHelper {
    public static Integer getHistoricalFigureKills(HistoricalFigure hf) {
        return getAllHistoricalFigureKills().getOrDefault(hf.getId(), 0);
    }

    /*
     * Returns all historical figure kills as a hash map.
     * 
     * Keys     = Historical figure id
     * Value    = Number of kills
     */
    public static HashMap<Integer, Integer> getAllHistoricalFigureKills() {
		HashMap<Integer, Integer> kills = new HashMap<>();
		World.getHistoricalEvents().forEach(e -> {
			if(e instanceof HfDiedEvent) {
				Integer id = ((HfDiedEvent)e).getSlayerHfId();
				kills.put(id, kills.getOrDefault(id, 0) + 1);
			}
		});
		return kills;
	}
}