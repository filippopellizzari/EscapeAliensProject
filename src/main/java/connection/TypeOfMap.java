package connection;

import java.io.Serializable;

public class TypeOfMap implements Serializable{
	/**
	 * 
	 */
	private String mapName;
	private String typeMap;
	public TypeOfMap(String mapName, String typeMap) {
		super();
		this.mapName = mapName;
		this.typeMap = typeMap;
	}
	/**
	 * @return the mapName
	 */
	public String getMapName() {
		return mapName;
	}
	/**
	 * @return the typeMap
	 */
	public String getTypeMap() {
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
		if (mapName == null) {
			if (other.mapName != null)
				return false;
		} else if (!mapName.equals(other.mapName))
			return false;
		if (typeMap == null) {
			if (other.typeMap != null)
				return false;
		} else if (!typeMap.equals(other.typeMap))
			return false;
		return true;
	}
	
	
}
