/**
 * Factory class to create Renderer instances based on type.
 * @author aron isaacs
 * @see Renderer
 */
public class RendererFactory {

    /**
     * Default constructor.
     */
    public RendererFactory() {
    }

    /**
     * Builds and returns a Renderer instance based on the specified type.
     * @param rendererType the type of renderer to create ("console" or "void")
     * @param size the size of the board (used for console renderer)
     * @return the created Renderer instance or null if the type is unrecognized
     */
    public  Renderer buildRenderer(String rendererType, int size) {
        // Create and return the appropriate Renderer instance based on the rendererType
        // note the use of Java 14+ switch expression
        return switch (rendererType.toLowerCase()) {
            case "console" -> new ConsoleRenderer(size);
            case "void" -> new VoidRenderer();
            default ->  null;
        };
    }
}