package com.amazonws.lambda.function.example.test.entity;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonws.lambda.function.example.util.Constants;

/**
 * 
 * @author Andres Solorzano
 * 
 */
@DynamoDBTable(tableName = Constants.DYNAMODB_DEVICE_TABLE_NAME)
public class DeviceEntity implements Serializable {

	private static final long serialVersionUID = -2971085261738930073L;

	/** Device identifier. */
	@DynamoDBHashKey(attributeName = "id")
	private Integer id;

	/** Sector identifier. */
	@DynamoDBRangeKey(attributeName = "sectorId")
	private String sectorId;

	/** Device name. */
	@DynamoDBAttribute(attributeName = "nombre")
	private String nombre;

	/** Device type. */
	@DynamoDBAttribute(attributeName = "tipo")
	private String tipo;

	/** Device status. */
	@DynamoDBAttribute(attributeName = "estado")
	private String estado;

	/**
	 * Class constructor.
	 */
	public DeviceEntity() {
		// Nothing to do.
	}

	public DeviceEntity(Integer id, String sectorId, String nombre, String estado, String tipo) {
		this.id = id;
		this.sectorId = sectorId;
		this.nombre = nombre;
		this.estado = estado;
		this.tipo = tipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSectorId() {
		return sectorId;
	}

	public void setSectorId(String sectorId) {
		this.sectorId = sectorId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeviceEntity other = (DeviceEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DeviceEntity [id=" + id + ", sectorId=" + sectorId + ", nombre=" + nombre + ", tipo=" + tipo
				+ ", estado=" + estado + "]";
	}

}
