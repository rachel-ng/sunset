package com.google.common.hash;

import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;

@ElementTypesAreNonnullByDefault
@Immutable
final class Crc32cHashFunction extends AbstractHashFunction {
    static final HashFunction CRC_32_C = new Crc32cHashFunction();

    public int bits() {
        return 32;
    }

    Crc32cHashFunction() {
    }

    public Hasher newHasher() {
        return new Crc32cHasher();
    }

    public String toString() {
        return "Hashing.crc32c()";
    }

    static final class Crc32cHasher extends AbstractStreamingHasher {
        static final int[] BYTE_TABLE = {0, -227835133, -516198153, 324072436, -946170081, 904991772, 648144872, -724933397, -1965467441, 2024987596, 1809983544, -1719030981, 1296289744, -1087877933, -1401372889, 1578318884, 274646895, -499825556, -244992104, 51262619, -675000208, 632279923, 922689671, -996891772, -1702387808, 1760304291, 2075979607, -1982370732, 1562183871, -1351185476, -1138329528, 1313733451, 549293790, -757723683, -1048117719, 871202090, -416867903, 357341890, 102525238, -193467851, -1436232175, 1477399826, 1264559846, -1187764763, 1845379342, -1617575411, -1933233671, 2125378298, 820201905, -1031222606, -774358714, 598981189, -143008082, 85089709, 373468761, -467063462, -1170599554, 1213305469, 1526817161, -1452612982, 2107672161, -1882520222, -1667500394, 1861252501, 1098587580, -1290756417, -1606390453, 1378610760, -2032039261, 1955203488, 1742404180, -1783531177, -878557837, 969524848, 714683780, -655182201, 205050476, -28094097, -318528869, 526918040, 1361435347, -1555146288, -1340167644, 1114974503, -1765847604, 1691668175, 2005155131, -2047885768, -604208612, 697762079, 986182379, -928222744, 476452099, -301099520, -44210700, 255256311, 1640403810, -1817374623, -2130844779, 1922457750, -1503918979, 1412925310, 1197962378, -1257441399, -350237779, 427051182, 170179418, -129025959, 746937522, -554770511, -843174843, 1070968646, 1905808397, -2081171698, -1868356358, 1657317369, -1241332974, 1147748369, 1463399397, -1521340186, -79622974, 153784257, 444234805, -401473738, 1021025245, -827320098, -572462294, 797665321, -2097792136, 1889384571, 1674398607, -1851340660, 1164749927, -1224265884, -1537745776, 1446797203, 137323447, -96149324, -384560320, 461344835, -810158936, 1037989803, 781091935, -588970148, -1834419177, 1623424788, 1939049696, -2114449437, 1429367560, -1487280117, -1274471425, 1180866812, 410100952, -367384613, -112536529, 186734380, -538233913, 763408580, 1053836080, -860110797, -1572096602, 1344288421, 1131464017, -1323612590, 1708204729, -1749376582, -2065018290, 1988219213, 680717673, -621187478, -911630946, 1002577565, -284657034, 493091189, 238226049, -61306494, -1307217207, 1082061258, 1395524158, -1589280451, 1972364758, -2015074603, -1800104671, 1725896226, 952904198, -894981883, -638100751, 731699698, -11092711, 222117402, 510512622, -335130899, -1014159676, 837199303, 582374963, -790768336, 68661723, -159632680, -450051796, 390545967, 1230274059, -1153434360, -1469116676, 1510247935, -1899042540, 2091215383, 1878366691, -1650582816, -741088853, 565732008, 854102364, -1065151905, 340358836, -433916489, -177076669, 119113024, 1493875044, -1419691417, -1204696685, 1247431312, -1634718085, 1828433272, 2141937292, -1916740209, -483350502, 291187481, 34330861, -262120466, 615137029, -691946490, -980332558, 939183345, 1776939221, -1685949482, -1999470558, 2058945313, -1368168502, 1545135305, 1330124605, -1121741762, -210866315, 17165430, 307568514, -532767615, 888469610, -962626711, -707819363, 665062302, 2042050490, -1948470087, -1735637171, 1793573966, -1104306011, 1279665062, 1595330642, -1384295599};
        private static final ByteBuffer EMPTY = ByteBuffer.allocate(0);
        static final int INVERSE_COMPUTE_FOR_WORD_OF_ALL_1S = -287056435;
        static final int[][] STRIDE_TABLE = {new int[]{0, 819083365, 1638166730, 1366706351, -1018633836, -207955471, -1561554594, -1841387205, -2088913447, -1280340548, -489044717, -770966154, 1077236813, 1894214696, 563160199, 289610978, 51846467, 868558118, 1655926153, 1382110700, -1067451177, -259144526, -1577350115, -1859537800, -2140493670, -1330081537, -506537904, -786636747, 1126320398, 1945137515, 579221956, 307495329, 103692934, 922485475, 1737116236, 1465414185, -983114990, -172694665, -1530745896, -1810852931, -2058121377, -1249790150, -453509227, -735721488, 1176202955, 1992906414, 666836481, 393029220, 87631813, 904601504, 1688033039, 1414492010, -965622191, -157024716, -1479166309, -1761112322, -2042326500, -1231639943, -404692266, -684533069, 1158443912, 1977502701, 614990658, 343554855, 207385868, 1015958889, 1844970950, 1563049379, -820734824, -3756803, -1364138926, -1637688265, -1893613355, -1074530128, -293228513, -564688774, 1281958209, 2092636452, 768430475, 488597998, 256600143, 1067012138, 1861163141, 1581064416, -872183333, -53366338, -1381500655, -1653227148, -1942561386, -1125849613, -309154468, -582970055, 1333672962, 2141979751, 786058440, 503870637, 175263626, 983594991, 1809203008, 1526990629, -918901218, -102197637, -1465983276, -1739790671, -1995441581, -1176649162, -391411047, -663112964, 1246174151, 2056594338, 736324365, 456217448, 157635273, 968321708, 1757487619, 1477646950, -902974627, -83915976, -1417034857, -1688470542, -1978079472, -1161109643, -339961894, -613503041, 1229981316, 2038578913, 687109710, 405163563, 414771736, 678089341, 2031917778, 1238278839, -605025396, -350080023, -1168868538, -1970912477, -1681563711, -1424529500, -94294261, -894232722, 1485684309, 1751090736, 959041183, 167507706, 464516955, 729665342, 2047575953, 1255784436, -655943985, -399167830, -1186765307, -1986961824, -1731050878, -1476363545, -109694392, -911996371, 1536860950, 1799920499, 977195996, 183299001, 513200286, 776268027, 2134024276, 1340119089, -572641014, -317978257, -1132838464, -1935115867, -1645517497, -1388749534, -61926003, -862114328, 1587774675, 1852947638, 1057485849, 265669756, 495046109, 760477112, 2082848023, 1291289970, -557241271, -300215252, -1083351933, -1883282202, -1627621372, -1372700575, -11007794, -813027157, 1572116880, 1835442677, 1007741274, 214094143, 350527252, 607561585, 1967189982, 1167251387, -676561280, -411154715, -1240986038, -2032519633, -1751569715, -1488252248, -163751417, -957390238, 1423035225, 1677980476, 896908179, 94864374, 401834583, 656521778, 1985475229, 1183173368, -725916733, -462857306, -1256254711, -2050151572, -1802618994, -1537470485, -181778620, -973570271, 1472648730, 1729425023, 912434896, 112238261, 315270546, 572038647, 1936643416, 1136454973, -779992058, -514818973, -1339673396, -2131489623, -1850273717, -1587206098, -267165567, -1061070620, 1392505311, 1647167930, 861634837, 59357552, 299743441, 554664116, 1887029275, 1085010046, -761964219, -498638560, -1288623729, -2082270742, -1835004664, -1569573523, -217809470, -1009367641, 1374219420, 1631245561, 810327126, 10396723}, new int[]{0, 1409766726, -1475433844, -66453558, 1441866729, 32929455, -33584795, -1443308509, -1411233838, -1764716, 65858910, 1475065880, -32283589, -1441517187, 1444794039, 35298289, 1378416981, 103787539, -98151463, -1373567329, 131717820, 1407094778, -1344835536, -70245002, -104153465, -1379013695, 1371804683, 96682317, -1405379218, -130234328, 70596578, 1345479332, -1538133334, -262756372, 207575078, 1482165600, -241118909, -1515748347, 1504607183, 229191305, 263435640, 1538580542, -1480777740, -205894990, 1514313361, 239453143, -229884899, -1505007269, -159839233, -1568776519, 1584898419, 175174709, -1551357930, -141591216, 193364634, 1602344924, 1570458669, 161225067, -174725471, -1584221209, 141193156, 1550662274, -1604008632, -194801650, 1297060773, 424199907, -448710359, -1320784785, 415150156, 1287251210, -1330636096, -457748602, -424815497, -1297448655, 1319337723, 446966717, -1285752930, -413424936, 458382610, 1330972756, 526871280, 1264598966, -1217806212, -479292102, 1251363097, 512826463, -493296747, -1231046957, -1266340574, -528320412, 478906286, 1217188584, -512487733, -1250731123, 1232774215, 494792961, -383885041, -1122421687, 1091619715, 353869509, -1125170458, -387442784, 350349418, 1088863532, 1123821277, 385577883, -353203119, -1091184361, 386729268, 1124749426, -1090277448, -351995138, -1154049958, -281948900, 322450134, 1195337616, -288900173, -1161761035, 1187582271, 315507833, 282386312, 1154714318, -1193642748, -321052606, 1160117345, 287484199, -315926803, -1188297813, -1700845750, -828870132, 848399814, 1721161856, -814873437, -1687596571, 1734389295, 862452585, 830300312, 1702507998, -1720464876, -847995054, 1686913905, 814421559, -863835651, -1736065861, -927474145, -1666151591, 1622766739, 884875733, -1656291850, -918443856, 893933434, 1632567868, 1666554317, 928173195, -883215551, -1621334521, 916765220, 1654910818, -1633021784, -894614034, 1053742560, 1791590566, -1765769364, -1027134934, 1799353865, 1060676431, -1020175227, -1758066237, -1792241102, -1054095500, 1025652926, 1764060664, -1059212837, -1797593955, 1758665559, 1020546577, 1827024053, 954300915, -991394247, -1863330945, 957812572, 1829788186, -1860590128, -987828074, -956011673, -1828504031, 1862975979, 990745773, -1829418866, -957211192, 989585922, 1862055748, -674188049, -2083003991, 2145263203, 735660837, -2111727866, -702102976, 707739018, 2116577484, 2083713853, 674604667, -734242383, -2143613705, 700698836, 2110031250, -2117240232, -708169954, -2047324742, -771811076, 771155766, 2045882992, -804688301, -2079442155, 2013775071, 738234777, 773458536, 2048745262, -2045468444, -770443870, 2079009153, 804027591, -739933427, -2015177141, 1937851973, 663098115, -611324727, -1886865009, 644900268, 1920413930, -1904292064, -629564826, -664600169, -1939581743, 1886235419, 610991709, -1919802754, -644516040, 631015666, 1906040244, 564772624, 1974397526, -1985538660, -576700198, 1951964409, 543148479, -598329739, -2007932109, -1974732606, -565400188, 574968398, 1984038664, -541402325, -1950511507, 2008314279, 598942945}, new int[]{0, 1737424129, -820119038, -1466759421, -1680375051, -61243404, 1422555383, 860128758, 843281179, 1439534618, -44136167, -1697614824, -1449856530, -837153553, 1720257516, 17299181, 1686562358, 50862903, -1415898060, -870981323, -11443005, -1730176574, 810327745, 1472357312, 1455789357, 827025452, -1713869009, -27880914, -854452264, -1432556839, 34598362, 1702957275, -921842580, -1367133843, 101725806, 1637796719, 1390038681, 894743448, -1647856485, -95860326, -78725257, -1665121674, 877872501, 1407039604, 1620655490, 118997123, -1350252672, -938853759, -1383388582, -905586853, 1654050904, 85470553, 912042159, 1372738990, -113159507, -1630556244, -1614259903, -129588160, 1356178243, 928734786, 69196724, 1670457013, -889052746, -1400054601, -1745359319, -260500696, 1491735595, 1063577898, 203451612, 1806602717, -1019373858, -1531745313, -1514889934, -1036361677, 1789486896, 220699185, 1046683591, 1508762310, -243341883, -1762649916, -210696161, -1795164898, 1013772829, 1541541660, 1755745002, 254310379, -1480888088, -1070232087, -1053656316, -1497594363, 237994246, 1772190727, 1525021169, 1030423792, -1778908173, -227082510, 1593451077, 963960644, -1847076793, -160881338, -986865488, -1566351951, 170941106, 1841211315, 1824084318, 188197983, -1549489316, -1003858339, -143731797, -1864356182, 947071401, 1610470568, 981255283, 1576155506, -178176399, -1829780624, -1582610810, -970605689, 1857469572, 154681733, 138393448, 1873889897, -954053270, -1599295381, -1813498467, -194590564, 1559613343, 997929630, 704883363, 1301108642, -450998111, -2104447584, -1311496106, -698690217, 2127155796, 424095573, 406903224, 2144216249, -681761862, -1328292165, -2087232691, -468081076, 1284153679, 721706062, 1317358741, 688632212, -2120698217, -434746474, -715993504, -1294191775, 441398370, 2109852003, 2093367182, 457753231, -1277442676, -732612467, -418267781, -2137046918, 671893369, 1333967480, -485595441, -2071946290, 739482829, 1268605388, 2027545658, 525801787, -1211883976, -800398535, -783477292, -1228674859, 508620758, 2044596951, 1251645217, 756312608, -2054722269, -502689758, -2021097223, -536445448, 1217755899, 790333434, 475988492, 2077359885, -750585842, -1261697777, -1244924958, -767226141, 2060847584, 492369121, 773615895, 1234340886, -519992555, -2037418476, -1108065142, -629491317, 1927921288, 359089033, 639878783, 1101871998, -381797251, -1901018756, -1883818095, -398865776, 1084935571, 656683154, 341882212, 1944995941, -612544666, -1124879769, -646798660, -1090756675, 376395966, 1910613439, 1118117961, 623631688, -1917265333, -365549750, -349056601, -1933628250, 606874533, 1134745252, 1894142802, 392736339, -1074026160, -663399343, 1962510566, 326596071, -1142656284, -596996123, -282195437, -2002716910, 540274705, 1203571984, 1186659325, 557057788, -1985543681, -299238146, -580028152, -1159494647, 309363466, 1979612683, 276786896, 2012320721, -547187502, -1192465965, -1951863771, -333049564, 1152718375, 591129382, 574365131, 1169350858, -316545079, -1968235832, -1175740610, -563781057, 1995859260, 293115965}, new int[]{0, -234091010, -504074995, 335044851, -972772117, 872980757, 670089702, -704852968, -1981468889, 2078876377, 1745961514, -1709355052, 1340179404, -1108505038, -1374294335, 1545200447, 371599551, -466999999, -137214542, 98453580, -803044268, 573475242, 836168025, -1009064793, -1614608488, 1842285158, 2117560981, -1942263957, 1506257779, -1412716915, -1204566402, 1245694848, 743199102, -566207360, -843563917, 1068773773, -364315243, 407171179, 196907160, -105865882, -1515618727, 1470460839, 1146950484, -1236197718, 1672336050, -1851662516, -2108047425, 1884664385, 980056513, -932809665, -610396980, 697440562, -59845334, 241359060, 496678951, -275335719, -1282451738, 1099127576, 1383807979, -1602800107, 1972107789, -2021132301, -1803577600, 1718852350, 1486398204, -1433096446, -1184051215, 1264632335, -1633939945, 1821377513, 2137547546, -1922797852, -780300837, 594640933, 814342358, -1031410392, 393814320, -445305650, -158512067, 75579843, 1321110083, -1129150531, -1354045618, 1564928688, -2001066328, 2058758998, 1766738853, -1690155429, -950295196, 894937242, 649054313, -727464553, 23005583, -212663183, -525638526, 312961404, 1960113026, -2032599428, -1793024369, 1730974577, -1294966935, 1088180887, 1394881124, -1591197798, -47063899, 255709531, 482718120, -288768938, 993357902, -918979152, -624878269, 684527805, 1660083005, -1862347069, -2096712144, 1896528846, -1527351338, 1459255848, 1157765851, -1223813339, -350751718, 421263844, 182688023, -118516503, 756242673, -551594737, -857262596, 1055602690, -1322170888, 1128089606, 1355098357, -1563876085, 2000021779, -2059803411, -1765702626, 1691191776, 953445087, -891787487, -652212270, 724306476, -19872204, 215796682, 522496825, -316102969, -1491636921, 1427857593, 1189281866, -1259401804, 1628684716, -1826632622, -2132300639, 1928044895, 787628640, -587313250, -821678227, 1024074387, -386470261, 452649845, 151159686, -82932104, -1652747130, 1869683064, 2089384331, -1903856523, 1534703725, -1451903597, -1165109920, 1216469150, 345521057, -426494369, -177449300, 123755346, -761489590, 546347700, 862517831, -1050347591, -1956955079, 2035757511, 1789874484, -1734124342, 1298108626, -1085039316, -1398014497, 1588064289, 46011166, -256762144, -481657325, 289829869, -994394123, 917942795, 625922808, -683483386, -374741244, 463858426, 140347913, -95320073, 799886319, -576633327, -833018142, 1012214556, 1615644707, -1841248803, -2118605522, 1941219536, -1505205048, 1413769526, 1203505605, -1246755781, -7352389, 226738757, 511419062, -327700664, 965436240, -880316754, -662761891, 712180643, 1986715804, -2073629342, -1751216751, 1704099951, -1334948745, 1113735561, 1369055610, -1550439292, -974801286, 938064772, 605150071, -702687607, 65084049, -236120209, -501909604, 270105186, 1275107677, -1106471773, -1376455600, 1610152366, -1979435594, 2013804616, 1810913467, -1711516347, -742154555, 567251771, 842527688, -1069810122, 365376046, -406110256, -197959901, 104813277, 1512485346, -1473594340, -1143808785, 1239339281, -1675485943, 1848512759, 2111205380, -1881506310}};
        private int crc0 = INVERSE_COMPUTE_FOR_WORD_OF_ALL_1S;
        private int crc1 = 0;
        private int crc2 = 0;
        private int crc3 = 0;
        private boolean finished = false;

        Crc32cHasher() {
            super(16);
        }

        /* access modifiers changed from: protected */
        public void process(ByteBuffer byteBuffer) {
            if (!this.finished) {
                while (byteBuffer.remaining() >= 16) {
                    this.crc0 = computeForWord(this.crc0);
                    this.crc1 = computeForWord(this.crc1);
                    this.crc2 = computeForWord(this.crc2);
                    this.crc3 = computeForWord(this.crc3);
                    this.crc0 ^= byteBuffer.getInt();
                    this.crc1 ^= byteBuffer.getInt();
                    this.crc2 ^= byteBuffer.getInt();
                    this.crc3 ^= byteBuffer.getInt();
                }
                return;
            }
            throw new IllegalStateException("The behavior of calling any method after calling hash() is undefined.");
        }

        /* access modifiers changed from: protected */
        public void processRemaining(ByteBuffer byteBuffer) {
            if (!this.finished) {
                int combine = combine(0, this.crc0);
                this.crc0 = combine;
                int combine2 = combine(combine, this.crc1);
                this.crc0 = combine2;
                int combine3 = combine(combine2, this.crc2);
                this.crc0 = combine3;
                this.crc0 = combine(combine3, this.crc3);
                while (byteBuffer.hasRemaining()) {
                    this.crc0 = (this.crc0 >>> 8) ^ BYTE_TABLE[(byteBuffer.get() ^ this.crc0) & 255];
                }
                this.finished = true;
            }
        }

        /* access modifiers changed from: protected */
        public HashCode makeHash() {
            if (!this.finished) {
                processRemaining(EMPTY);
            }
            return HashCode.fromInt(~this.crc0);
        }

        static int computeForWord(int i) {
            int[][] iArr = STRIDE_TABLE;
            return iArr[0][i >>> 24] ^ ((iArr[3][i & 255] ^ iArr[2][(i >>> 8) & 255]) ^ iArr[1][(i >>> 16) & 255]);
        }

        static int combine(int i, int i2) {
            int i3 = i ^ i2;
            for (int i4 = 0; i4 < 4; i4++) {
                i3 = BYTE_TABLE[i3 & 255] ^ (i3 >>> 8);
            }
            return i3;
        }
    }
}
