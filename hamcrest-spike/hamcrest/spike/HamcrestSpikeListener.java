/**
 * 
 */
package hamcrest.spike;


public interface HamcrestSpikeListener {
    void receives(Event event, String otherwise);
    void alsoReceives(Event event);
  }