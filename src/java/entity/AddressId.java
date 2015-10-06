package entity;

import java.io.Serializable;

public class AddressId implements Serializable {
    String street;
    String additionalInfo;

    public AddressId() {
    }
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (this.street != null ? this.street.hashCode() : 0);
        hash = 47 * hash + (this.additionalInfo != null ? this.additionalInfo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AddressId other = (AddressId) obj;
        if ((this.street == null) ? (other.street != null) : !this.street.equals(other.street)) {
            return false;
        }
        if ((this.additionalInfo == null) ? (other.additionalInfo != null) : !this.additionalInfo.equals(other.additionalInfo)) {
            return false;
        }
        return true;
    }
}
