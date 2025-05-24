/*  Compile:  javac InfoOverlay.java
 *  Run    :  java  InfoOverlay "Ginkgo biloba"
 * ========================================================== */
import java.util.Map;

abstract class Leaf {
    private final String scientific;   // encapsulated
    private final String common;

    protected Leaf(String scientific, String common) {
        this.scientific = scientific;
        this.common     = common;
    }

    public String getScientificName() { return scientific; }
    public String getCommonName()     { return common;     }

    public abstract String getColorShape();
    public abstract String getOriginHabit();
    public abstract String getLifespanType();
    public abstract String getEnvBenefits();

    @Override public String toString() {
        return """
               ╭───────────────────────────────────────────────────────────╮
               │  %s  (%s)                                                 │
               ├───────────────────────────────────────────────────────────┤
               │ Leaf Color & Shape : %s
               │ Tree Origin/Habit  : %s
               │ Lifespan / Type    : %s
               │ Environmental Use  : %s
               ╰───────────────────────────────────────────────────────────╯
               """.formatted(common, scientific,
                             getColorShape(),
                             getOriginHabit(),
                             getLifespanType(),
                             getEnvBenefits());
    }
}

class AcerPalmatum extends Leaf {
    AcerPalmatum() { super("Acer palmatum", "Japanese Maple"); }
    public String getColorShape()  { return "Thin, palm-shaped (5-9 lobes); green/red/purple and orange-red in fall"; }
    public String getOriginHabit() { return "Small deciduous tree (6-10 m), native to Japan-Korea-China"; }
    public String getLifespanType(){ return "Deciduous; healthy trees live 100 + years"; }
    public String getEnvBenefits() { return "Shade, ornamental, traditional medicine, lowers urban heat"; }
}

class CedrusDeodara extends Leaf {
    CedrusDeodara() { super("Cedrus deodara", "Himalayan Cedar"); }
    public String getColorShape()  { return "Needle-like leaves 2.5-5 cm, bright green to bluish-silver (dense clusters)"; }
    public String getOriginHabit() { return "Large evergreen conifer (40-50 m), native to the Himalayas"; }
    public String getLifespanType(){ return "Evergreen; trees can live > 500 years (each needle 3-6 y)"; }
    public String getEnvBenefits() { return "Fragrant, rot-resistant wood; canopy stabilises soil & retains water; CO₂ sink"; }
}

class CercisChinensis extends Leaf {
    CercisChinensis() { super("Cercis chinensis", "Chinese Redbud"); }
    public String getColorShape()  { return "Heart-shaped 6-14 cm, glossy green and yellow in autumn"; }
    public String getOriginHabit() { return "Small tree/shrub (3-15 m); pink flowers on bare branches in early spring"; }
    public String getLifespanType(){ return "Deciduous; lifespan > 50 years"; }
    public String getEnvBenefits() { return "Early nectar for pollinators; drought-tolerant; bark/buds used medicinally"; }
}

class CitrusReticulata extends Leaf {
    CitrusReticulata() { super("Citrus reticulata", "Mandarin Orange"); }
    public String getColorShape()  { return "Small, glossy, lance-shaped leaves, dark green"; }
    public String getOriginHabit() { return "Fruit tree (4-8 m) with fragrant mandarin oranges"; }
    public String getLifespanType(){ return "Evergreen; sheds old leaves continuously"; }
    public String getEnvBenefits() { return "Vitamin-C fruit; leaves filter dust; roots prevent soil erosion"; }
}

class GinkgoBiloba extends Leaf {
    GinkgoBiloba() { super("Ginkgo biloba", "Maidenhair Tree"); }
    public String getColorShape()  { return "Fan-shaped 5-10 cm; bright green and golden yellow in fall"; }
    public String getOriginHabit() { return "Ancient gymnosperm (20-35 m); native to East Asia (living fossil)"; }
    public String getLifespanType(){ return "Deciduous; can live > 1 000 years"; }
    public String getEnvBenefits() { return "Extremely pollution-tolerant; absorbs NOx; drought/salt resistant"; }
}

class LiriodendronChinense extends Leaf {
    LiriodendronChinense() { super("Liriodendron chinense", "Chinese Tulip Tree"); }
    public String getColorShape()  { return "Broad leaf with 4 lobes, 8-15 cm; bright green and golden in autumn"; }
    public String getOriginHabit() { return "Large tree (30-40 m) with tulip-like green-yellow flowers"; }
    public String getLifespanType(){ return "Deciduous; fast-growing (~150 y)"; }
    public String getEnvBenefits() { return "Light wood; nectar-rich flowers; wide crown captures CO₂ & provides shade"; }
}

class NeriumOleander extends Leaf {
    NeriumOleander() { super("Nerium oleander", "Oleander"); }
    public String getColorShape()  { return "Thick, sword-shaped leaves 5-21 cm, whorled; dark green year-round"; }
    public String getOriginHabit() { return "Shrub/small tree (2-6 m) native to Mediterranean climates"; }
    public String getLifespanType(){ return "Evergreen; lives 30-80 years"; }
    public String getEnvBenefits() { return "Tolerates drought & salt; leaves biomonitor heavy metals/PM (toxic if ingested)"; }
}

class LeafFactory {
    private static final Map<String, Leaf> registry = Map.ofEntries(
        Map.entry("acer palmatum",           new AcerPalmatum()),
        Map.entry("cedrus deodara",          new CedrusDeodara()),
        Map.entry("cercis chinensis",        new CercisChinensis()),
        Map.entry("citrus reticulata",       new CitrusReticulata()),
        Map.entry("ginkgo biloba",           new GinkgoBiloba()),
        Map.entry("liriodendron chinense",   new LiriodendronChinense()),
        Map.entry("nerium oleander",         new NeriumOleander())
    );

    static Leaf create(String scientificName) {
        return registry.get(scientificName.toLowerCase().trim());
    }
}

public class InfoOverlay {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java InfoOverlayDemo \"Ginkgo biloba\"");
            return;
        }
        Leaf leaf = LeafFactory.create(args[0]);
        if (leaf == null) {
            System.out.println("Species not found in catalogue.");
        } else {
            System.out.println(leaf);   // polymorphic toString() renders card
        }
    }
}
