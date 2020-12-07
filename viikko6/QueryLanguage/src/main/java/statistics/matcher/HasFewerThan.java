
package statistics.matcher;

import statistics.Player;

public class HasFewerThan implements Matcher {
    
    private Matcher matcher;
    
    public HasFewerThan(int value, String category) {
        this.matcher = new Not(new HasAtLeast(value, category));
    }

    public boolean matches(Player p) {
        return this.matcher.matches(p);
    }
    
    
}
