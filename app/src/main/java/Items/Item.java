package Items;

import com.example.doodle.Doodler;

import Platforms.Platform;

public interface Item {

    public void applyItemTo(Doodler doodler);
    public void deleteThis();

    public boolean isMarked();

}
