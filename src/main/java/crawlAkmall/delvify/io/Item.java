package crawlAkmall.delvify.io;

import java.util.ArrayList;

public class Item {
    public String itemTitle;
    public String designer;
    public String keyFeatures;
    public String compatibility;
    public String packageIncludes;
    public String productDim;
    public String netWeight;
    public String packageSize;
    public String materials;
    public String price;
    public String description;
    public ArrayList<String> urlLinks;

    public Item(String itemTitle, String designer, String keyFeatures, String compatibility, String packageIncludes, String productDim, String netWeight, String packageSize, String materials, String price, ArrayList<String> urlLinks) {
        this.itemTitle = itemTitle;
        this.designer = designer;
        this.keyFeatures = keyFeatures;
        this.compatibility = compatibility;
        this.packageIncludes = packageIncludes;
        this.productDim = productDim;
        this.netWeight = netWeight;
        this.packageSize = packageSize;
        this.materials = materials;
        this.price = price;
        this.urlLinks = urlLinks;
    }
}
