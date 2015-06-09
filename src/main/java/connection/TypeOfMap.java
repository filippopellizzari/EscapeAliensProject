package connection;

import java.io.Serializable;

public class TypeOfMap implements Serializable{
	private MapName mapName;
	private MapType typeMap;
	public TypeOfMap(MapName mapName, MapType typeMap) {
		super();
		this.mapName = mapName;
		this.typeMap = typeMap;
	}
	/**
	 * @return the mapName
	 */
	public MapName getMapName() {
		return mapName;
	}
	/**
	 * @return the typeMap
	 */
	public MapType getTypeMap() {
		return typeMap;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mapName == null) ? 0 : mapName.hashCode());
		result = prime * result + ((typeMap == null) ? 0 : typeMap.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TypeOfMap other = (TypeOfMap) obj;
		if (mapName != other.mapName)
			return false;
		if (typeMap != other.typeMap)
			return false;
		return true;
	}
	
	
	
}
