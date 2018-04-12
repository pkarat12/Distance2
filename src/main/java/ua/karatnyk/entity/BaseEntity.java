package ua.karatnyk.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
	private Date createdAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by_user_id", updatable = false)
	private UserEntity createdByUser;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;
	
	@Column(name = "last_updated_at"/*, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"*/)
	private Date lastUpdatedAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "last_updated_by_user_id")
	private UserEntity lastUpdateByUser;

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
		BaseEntity other = (BaseEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
