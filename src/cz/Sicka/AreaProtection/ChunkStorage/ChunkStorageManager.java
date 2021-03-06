package cz.Sicka.AreaProtection.ChunkStorage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.World;

import cz.Sicka.AreaProtection.Area.Area;
import cz.Sicka.AreaProtection.Utils.ChunkCalculate;

public class ChunkStorageManager {
	private Map<String, ChunkStorage> ChunkStorage = new HashMap<String, ChunkStorage>();
	
	private World world;

	public ChunkStorageManager(World world) {
		this.world = world;
	}

	public World getWorld() {
		return world;
	}
	
	public void addAreaToChunkStorages(Area area){
		List<String> s = ChunkCalculate.calculateChunksAndgetChunkNames(area.getHighX(), area.getHighZ(), area.getLowX(), area.getLowZ());
		area.setChunks(s);
		for(String chunkname : s){
			getChunkStorage(chunkname).addArea(area);
		}
	}
	
	public void removeAreaFromChunkStorages(Area area){
		List<String> s = area.getChunks();
		for(String chunkname : s){
			getChunkStorage(chunkname).removeArea(area);
		}
	}
	
	/**
	 * @param ChunkStorage name that you want to get.
	 * @return ChunkStorage
	 * Note: if ChunkStorage not set -> Create new ChunkStorage and save them to map
	 */
	public ChunkStorage getChunkStorage(String chunkName){
		if(ChunkStorage.containsKey(chunkName)){
			return ChunkStorage.get(chunkName);
		}else{
			ChunkStorage ch = new ChunkStorage(chunkName);
			ChunkStorage.put(chunkName, ch);
			return ch;
		}
	}
}
