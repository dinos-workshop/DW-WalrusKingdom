import java.util.ArrayList;

public class OldMaterialData {
    int id;
    String name;
    boolean isSolid;
    int PictureID;
    int TriggerID;
    public static OldMaterialData[] oldMaterialData = new OldMaterialData[230];

    public OldMaterialData(int _id, String _name, boolean _isSolid, int _PictureID, int _TriggerID) {
        this.id = _id;                    // used in the matrix-map
        this.name = _name;                // just for fun
        this.isSolid = _isSolid;        // to check if walking through this is possible
        this.PictureID = _PictureID;    // picture link (maybe later replace this with a bitmap object or something)
        this.TriggerID = _TriggerID;    // give a number to every special event that is triggered on walk-overs.
    }

    public static void AddNew(int _id, String _name, boolean _isSolid, int _PictureID, int _TriggerID) {
        oldMaterialData[_id] = new OldMaterialData(_id, _name, _isSolid, _PictureID, _TriggerID);
    }
    public static void Import(MaterialManager materialManager) {
        for (OldMaterialData oldMaterialData : OldMaterialData.oldMaterialData) {
            ArrayList<String> pictureIDs = new ArrayList<String>();
            pictureIDs.add(oldMaterialData.PictureID+"");
            materialManager.addMaterial(new Material(oldMaterialData.id, oldMaterialData.name, oldMaterialData.isSolid, pictureIDs));
        }
    }



    // OLD MATERIAL DATA
    public static void LoadOldMaterialData() {
        AddNew(0, "void", false, 0, 1);		// no walrus shall ever go there
        AddNew(1, "walrus", false, 1, 2);		// a walrus
        AddNew(2, "walrus", true, 2, 2);
        AddNew(3, "walrus", false, 3, 2);
        AddNew(4, "walrus", false, 4, 2);
        AddNew(5, "walrus", false, 5, 2);
        AddNew(6, "walrus", false, 6, 2);
        AddNew(7, "walrus", false, 7, 2);
        AddNew(8, "walrus", false, 8, 2);
        AddNew(9, "void SOLID", true, 9, 3);	// used for menu
        AddNew(10, "grass", false, 10, 4);	// nice grass
        AddNew(11, "flower", false, 11, 5);	// nice flower
        AddNew(12, "flower", false, 12, 5);
        AddNew(13, "flower", false, 13, 5);
        AddNew(14, "flower", false, 14, 5);
        AddNew(15, "street", false, 15, 6);	// a street
        AddNew(16, "street", false, 16, 6);
        AddNew(17, "street", false, 17, 6);
        AddNew(18, "street", false, 18, 6);
        AddNew(19, "street", false, 19, 6);
        AddNew(20, "house", false, 20, 7);	// a house
        AddNew(21, "house", false, 21, 7);
        AddNew(22, "house", false, 22, 7);
        AddNew(23, "house", false, 23, 7);
        AddNew(24, "house", false, 24, 7);
        AddNew(25, "house", false, 25, 7);
        AddNew(26, "house", true, 26, 7);
        AddNew(27, "house", true, 27, 7);
        AddNew(28, "house", true, 28, 7);
        AddNew(29, "house", true, 29, 7);
        AddNew(30, "house", true, 30, 7);
        AddNew(31, "house", true, 31, 7);
        AddNew(32, "house", true, 32, 7);
        AddNew(33, "house", true, 33, 7);
        AddNew(34, "house", true, 34, 7);
        AddNew(35, "house", true, 35, 7);
        AddNew(36, "house", true, 36, 7);
        AddNew(37, "house", true, 37, 7);
        AddNew(38, "house", true, 38, 7);
        AddNew(39, "house", true, 39, 8);		// DOOR
        AddNew(40, "house", true, 40, 8);		// DOOR
        AddNew(41, "house", true, 41, 7);
        AddNew(42, "house", true, 42, 7);
        AddNew(43, "house", true, 43, 7);
        AddNew(44, "house", true, 44, 7);
        AddNew(45, "house", true, 45, 7);
        AddNew(46, "statue", false, 46, 9);	// statue
        AddNew(47, "statue", false, 47, 9);
        AddNew(48, "statue", true, 48, 9);
        AddNew(49, "statue", true, 49, 9);
        AddNew(50, "house", false, 50, 7);
        AddNew(51, "house", false, 51, 7);
        AddNew(52, "house", false, 52, 7);
        AddNew(53, "house", false, 53, 7);
        AddNew(54, "house", false, 54, 7);
        AddNew(55, "house", false, 55, 7);
        AddNew(56, "house", true, 56, 7);
        AddNew(57, "house", true, 57, 7);
        AddNew(58, "house", true, 58, 7);
        AddNew(59, "house", true, 59, 7);
        AddNew(60, "house", true, 60, 7);
        AddNew(61, "house", true, 61, 7);
        AddNew(62, "house", true, 62, 7);
        AddNew(63, "house", true, 63, 7);
        AddNew(64, "house", true, 64, 7);
        AddNew(65, "house", true, 65, 7);
        AddNew(66, "house", true, 66, 7);
        AddNew(67, "house", true, 67, 7);
        AddNew(68, "house", true, 68, 7);
        AddNew(69, "house", true, 69, 8);		// DOOR
        AddNew(70, "house", true, 70, 8);		//DOOR
        AddNew(71, "house", true, 71, 7);
        AddNew(72, "house", true, 72, 7);
        AddNew(73, "house", true, 73, 7);
        AddNew(74, "house", true, 74, 7);
        AddNew(75, "house", true, 75, 7);
        AddNew(76, "sign", false, 76, 10);	// arrow signs NOT solid. Only text-signs are
        AddNew(77, "sign", false, 77, 10);
        AddNew(78, "sign", true, 78, 10);
        AddNew(79, "sign", true, 79, 10);
        AddNew(80, "house", false, 80, 7);
        AddNew(81, "house", false, 81, 7);
        AddNew(82, "house", false, 82, 7);
        AddNew(83, "house", false, 83, 7);
        AddNew(84, "house", true, 84, 7);
        AddNew(85, "house", true, 85, 7);
        AddNew(86, "house", true, 86, 7);
        AddNew(87, "house", true, 87, 7);
        AddNew(88, "house", true, 88, 7);
        AddNew(89, "house", true, 89, 7);
        AddNew(90, "house", true, 90, 7);
        AddNew(91, "house", true, 91, 7);
        AddNew(92, "house", true, 92, 7);
        AddNew(93, "house", true, 93, 7);
        AddNew(94, "house", true, 94, 7);
        AddNew(95, "house", true, 95, 7);
        AddNew(96, "house", true, 96, 7);
        AddNew(97, "house", true, 97, 7);
        AddNew(98, "house", true, 98, 7);
        AddNew(99, "house", true, 99, 8);		// DOOR
        AddNew(100, "house", true, 100, 8);	// DOOR
        AddNew(101, "house", true, 101, 7);
        AddNew(102, "house", true, 102, 7);
        AddNew(103, "house", true, 103, 7);
        AddNew(104, "house", true, 104, 7);
        AddNew(105, "house", true, 105, 7);
        AddNew(106, "tree", true, 106, 11);	// TREE
        AddNew(107, "tree", true, 107, 11);	// TREE
        AddNew(108, "tree", false, 108, 12);	// BEHIND TREE
        AddNew(109, "tree", true, 109, 11);	// TREE					// #######################
        AddNew(110, "street", false, 110, 6);
        AddNew(111, "street", false, 111, 6);
        AddNew(112, "street", false, 112, 6);
        AddNew(113, "street", false, 113, 6);
        AddNew(114, "statue", true, 114, 9);	// statue
        AddNew(115, "statue", true, 115, 0);	// statue
        AddNew(116, "tree", true, 116, 11);	// TREE
        AddNew(117, "tree", true, 117, 11);	// TREE
        AddNew(118, "tree", false, 118, 12);	// BEHIND TREE
        AddNew(119, "tree", true, 119, 11);	// TREE
        AddNew(120, "NotInUse", false, 120, 0);
        AddNew(121, "NotInUse", false, 121, 0);
        AddNew(122, "NotInUse", false, 122, 0);
        AddNew(123, "NotInUse", false, 123, 0);
        AddNew(124, "NotInUse", false, 124, 0);
        AddNew(125, "NotInUse", false, 125, 0);
        AddNew(126, "tree", true, 126, 11);	// TREE
        AddNew(127, "tree", true, 127, 11);	// TREE
        AddNew(128, "NotInUse", false, 128, 0);
        AddNew(129, "NotInUse", false, 129, 0);
        AddNew(130, "street", false, 130, 6);
        AddNew(131, "street", false, 131, 6);
        AddNew(132, "street", false, 132, 6);
        AddNew(133, "street", false, 133, 6);
        AddNew(134, "house", true, 134, 7);
        AddNew(135, "house", true, 135, 7);
        AddNew(136, "house", true, 136, 8);	// A DOOR
        AddNew(137, "house", true, 137, 8);	// A DOOR
        AddNew(138, "house", true, 138, 7);
        AddNew(139, "house", true, 139, 7);
        AddNew(140, "house", true, 140, 7);
        AddNew(141, "house", true, 141, 7);
        AddNew(142, "street", false, 142, 6);	// A sign should stand on this
        AddNew(143, "house", true, 143, 7);
        AddNew(144, "house", true, 144, 7);
        AddNew(145, "house", true, 145, 7);
        AddNew(146, "house", true, 146, 7);
        AddNew(147, "house", true, 147, 7);
        AddNew(148, "house", true, 148, 7);
        AddNew(149, "house", true, 149, 7);
        AddNew(150, "NotInUse", false, 150, 0);
        AddNew(151, "NotInUse", false, 151, 0);
        AddNew(152, "NotInUse", false, 152, 0);
        AddNew(153, "house", true, 153, 7);
        AddNew(154, "house", true, 154, 7);
        AddNew(155, "house", true, 155, 7);
        AddNew(156, "house", true, 156, 7);
        AddNew(157, "house", true, 157, 7);
        AddNew(158, "house", true, 158, 7);
        AddNew(159, "house", true, 159, 7);
        AddNew(160, "house", false, 160, 0);
        AddNew(161, "house", false, 161, 0);
        AddNew(162, "NotInUse", false, 162, 0);
        AddNew(163, "house", true, 163, 7);
        AddNew(164, "house", false, 164, 7);
        AddNew(165, "house", false, 165, 7);
        AddNew(166, "house", false, 166, 7);
        AddNew(167, "house", false, 167, 7);
        AddNew(168, "house", false, 168, 7);
        AddNew(169, "house", false, 169, 7);	// ############################# end
        AddNew(170, "walrus", false, 170, 2);
        AddNew(171, "walrus", false, 171, 2);
        AddNew(172, "walrus", false, 172, 2);
        AddNew(173, "walrus", false, 173, 2);
        AddNew(174, "walrus", false, 174, 2);
        AddNew(175, "walrus", false, 175, 2);
        AddNew(176, "walrus", false, 176, 2);
        AddNew(177, "walrus", false, 177, 2);
        AddNew(178, "walrus", false, 178, 2);
        AddNew(179, "walrus", false, 179, 2);
        AddNew(180, "walrus", false, 180, 2);
        AddNew(181, "walrus", false, 181, 2);
        AddNew(182, "walrus", false, 182, 2);
        AddNew(183, "walrus", false, 183, 2);
        AddNew(184, "walrus", false, 184, 2);
        AddNew(185, "walrus", false, 185, 2);
        AddNew(186, "walrus", false, 186, 2);
        AddNew(187, "walrus", false, 187, 2);
        AddNew(188, "walrus", false, 188, 2);
        AddNew(189, "walrus", false, 189, 2);
        AddNew(190, "Stone", true, 190, 2);	// Stone table
        AddNew(191, "-", false, 191, 2);
        AddNew(192, "-", false, 192, 2);
        AddNew(193, "-", false, 193, 2);
        AddNew(194, "-", false, 194, 2);
        AddNew(195, "-", false, 195, 2);
        AddNew(196, "-", false, 196, 2);
        AddNew(197, "library", true, 197, 7);
        AddNew(198, "library", true, 198, 7);
        AddNew(199, "library", true, 199, 7);
        AddNew(200, "library", true, 200, 7);
        AddNew(201, "library", true, 201, 7);
        AddNew(202, "library", true, 202, 7);
        AddNew(203, "library", true, 203, 7);
        AddNew(204, "library", true, 204, 7);
        AddNew(205, "library", true, 205, 7);
        AddNew(206, "library", true, 206, 7);
        AddNew(207, "library", true, 207, 7);
        AddNew(208, "library", true, 208, 7);
        AddNew(209, "library", true, 209, 7);
        AddNew(210, "-", false, 199, 2);
        AddNew(211, "-", false, 199, 2);
        AddNew(212, "library", true, 212, 7);
        AddNew(213, "library", true, 213, 7);
        AddNew(214, "library", true, 214, 7);
        AddNew(215, "library", true, 215, 7);
        AddNew(216, "library", true, 216, 7);
        AddNew(217, "library", true, 217, 7);
        AddNew(218, "library", true, 218, 7);
        AddNew(219, "library", true, 219, 7);
        AddNew(220, "-", false, 220, 2);
        AddNew(221, "-", false, 221, 2);
        AddNew(222, "-", false, 222, 2);
        AddNew(223, "-", false, 223, 2);
        AddNew(224, "library", true, 224, 2);
        AddNew(225, "library", true, 225, 2);
        AddNew(226, "library", true, 226, 2);
        AddNew(227, "library", true, 227, 2);
        AddNew(228, "library", true, 228, 2);
        AddNew(229, "library", true, 229, 2);

    }
}