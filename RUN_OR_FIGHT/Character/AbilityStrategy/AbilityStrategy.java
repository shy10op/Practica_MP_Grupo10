package Character.AbilityStrategy;

import Character.CharacterEspecific.*;

public interface AbilityStrategy {
    public interface Discipline {
        void discipline(Vampire vampire);
    }

    public interface Don {
        void don(Werewolf werewolf);
    }

    public interface Talent {
        void talent(Hunter hunter);
    }
}
