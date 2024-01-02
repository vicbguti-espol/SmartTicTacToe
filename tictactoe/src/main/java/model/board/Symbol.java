package model.board;

import java.util.Objects;

public class Symbol {
    Character character;

    public Symbol(Character character) {
        this.character = character;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Symbol other = (Symbol) obj;
        return Objects.equals(this.character, other.character);
    }

    @Override
    public String toString() {
        return character + "";
    }
}
