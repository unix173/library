package core.bookmedium;

/**
 * Created by ivsi on 2/2/2016.
 */
public class HardCoverBook implements BookMedium {

    @Override
    public MediumType getType() {
        return MediumType.HARD_COPY;
    }

    @Override
    public String getUniqueName() {
        return "PaperBook";
    }
}
