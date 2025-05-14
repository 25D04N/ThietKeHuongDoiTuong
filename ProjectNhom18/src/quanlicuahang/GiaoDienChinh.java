package quanlicuahang;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GiaoDienChinh extends JFrame {
    JPanel pnTaoDon,  pnQuanLyHoaDon, pnQuanLyNhanVien, pnQuanLySanPham, pnQuanLyKhachHang;
    JMenuBar menubar;
    JMenu mnHoaDon, mnQuanLiHeThong;
    JMenuItem mniQuanLyHoaDon, mniQuanLySanPham,mniQuanLyNhanVien, mniQuanLiKhachHang;
    CardLayout cardLayout;

    public GiaoDienChinh() {
        setTitle("Quản lý cửa hàng");
        setSize(1000, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // CardLayout cho content pane
        cardLayout = new CardLayout();
        getContentPane().setLayout(cardLayout);

        // Tạo menu
        menubar = new JMenuBar();
        mnHoaDon = new JMenu("Tạo hóa đơn");
        mnQuanLiHeThong = new JMenu("Chức năng quản lý");

        menubar.add(mnHoaDon);
        menubar.add(mnQuanLiHeThong);
        // menu item
        mniQuanLyHoaDon = new JMenuItem("Quản lý hóa đơn");
        mniQuanLyNhanVien = new JMenuItem("Quản lý nhân viên");
        mniQuanLySanPham = new JMenuItem("Quản lý sản phẩm");
        mniQuanLiKhachHang = new JMenuItem("Quản lý khách hàng");
        mnQuanLiHeThong.add(mniQuanLyHoaDon);
        mnQuanLiHeThong.add(mniQuanLySanPham);
        mnQuanLiHeThong.add(mniQuanLyNhanVien);
        mnQuanLiHeThong.add(mniQuanLiKhachHang);
        setJMenuBar(menubar);
        
        // Tạo các panel
        pnTaoDon = new JPanel(new BorderLayout());
        taoGiaoDienTaoDon();
        pnQuanLyHoaDon = new JPanel();
        pnQuanLyHoaDon.add(new JLabel("Quản lý hóa đơn"));

        pnQuanLyNhanVien = new JPanel();
        pnQuanLyNhanVien.add(new JLabel("Quản lý nhân viên"));

        pnQuanLySanPham = new JPanel();
        pnQuanLySanPham.add(new JLabel("Quản lý sản phẩm"));
        taoQuanLiSanPham();

        pnQuanLyKhachHang = new JPanel();
        pnQuanLyKhachHang.add(new JLabel("Quản lý khách hàng"));

        getContentPane().add(pnQuanLyHoaDon, "QLHoaDon");
        getContentPane().add(pnQuanLyNhanVien, "QLNhanVien");
        getContentPane().add(pnQuanLySanPham, "QLSanPham");
        getContentPane().add(pnQuanLyKhachHang, "QLKhachHang");
        
        
        // Thêm các panel vào CardLayout
        getContentPane().add(pnTaoDon, "TaoDon");
        cardLayout.show(getContentPane(), "TaoDon");

        // Thêm sự kiện chuyển layout
        mnHoaDon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(getContentPane(), "TaoDon");
            }
        });

       
        mniQuanLyHoaDon.addActionListener(e -> cardLayout.show(getContentPane(), "QLHoaDon"));
        mniQuanLyNhanVien.addActionListener(e -> cardLayout.show(getContentPane(), "QLNhanVien"));
        mniQuanLySanPham.addActionListener(e -> cardLayout.show(getContentPane(), "QLSanPham"));
        mniQuanLiKhachHang.addActionListener(e -> cardLayout.show(getContentPane(), "QLKhachHang"));
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    //Giao diện tạo hóa đơn
    private void taoGiaoDienTaoDon() {
        // Panel chính chứa bên trái và phải
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(0.4);

        // === Panel trái: Form hóa đơn ===
        JPanel pnTrai = new JPanel(new BorderLayout());
        JPanel pnThongTin = new JPanel(new GridLayout(4, 2, 10, 10));
        pnThongTin.setBorder(BorderFactory.createTitledBorder("HÓA ĐƠN BÁN LẺ"));

        // Các trường nhập liệu (tạo trước)
        JLabel lblMaHD = new JLabel("Mã hóa đơn");
        JTextField tfMaHD = new JTextField("HD002");

        JLabel lblNgayLap = new JLabel("Ngày lập hóa đơn");
        LocalDate date = LocalDate.now();
        JTextField tfNgayLap = new JTextField(date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear());

        JLabel lblTenNV = new JLabel("Tên nhân viên");
        JComboBox<String> cbTenNV = new JComboBox<>();
        

        JLabel lblSDT = new JLabel("SDT khách hàng");
        JTextField tfSDT = new JTextField();

        // Thêm vào panel thông tin
        pnThongTin.add(lblMaHD);      pnThongTin.add(tfMaHD);
        pnThongTin.add(lblNgayLap);   pnThongTin.add(tfNgayLap);
        pnThongTin.add(lblTenNV);     pnThongTin.add(cbTenNV);
        pnThongTin.add(lblSDT);       pnThongTin.add(tfSDT);

        // Bảng chi tiết món
        String[] columns = { "TÊN MÓN", "SỐ LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN" };
        DefaultTableModel modelChiTiet = new DefaultTableModel(columns, 0);
        JTable tblChiTiet = new JTable(modelChiTiet);
        JScrollPane scrollChiTiet = new JScrollPane(tblChiTiet);

        // Panel thanh toán
        JPanel pnThanhToan = new JPanel(new GridLayout(3, 2, 5, 5));
        pnThanhToan.setBorder(BorderFactory.createTitledBorder("Thanh toán"));

        JLabel lblTongTien = new JLabel("Tổng tiền");
        JTextField tfTongTien = new JTextField();

        JLabel lblPTTT = new JLabel("PTTT");
        JRadioButton rdTienMat = new JRadioButton("Tiền mặt");
        JRadioButton rdChuyenKhoan = new JRadioButton("Chuyển khoản");
        JRadioButton rdTheNganHang = new JRadioButton("Thẻ NH");
        ButtonGroup bgPTTT = new ButtonGroup();
        bgPTTT.add(rdTienMat);
        bgPTTT.add(rdChuyenKhoan);
        bgPTTT.add(rdTheNganHang);
        JPanel ptPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ptPanel.add(rdTienMat);
        ptPanel.add(rdChuyenKhoan);
        ptPanel.add(rdTheNganHang);

        JLabel lblTienKhach = new JLabel("Tiền khách đưa");
        JTextField tfTienKhach = new JTextField();

        pnThanhToan.add(lblTongTien);    pnThanhToan.add(tfTongTien);
        pnThanhToan.add(lblPTTT);        pnThanhToan.add(ptPanel);
        pnThanhToan.add(lblTienKhach);   pnThanhToan.add(tfTienKhach);

        // Nút thao tác
        JButton btnHuy = new JButton("Hủy hóa đơn");
        JButton btnXuat = new JButton("Xuất hóa đơn");
        JPanel pnNut = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnNut.add(btnHuy);
        pnNut.add(btnXuat);

        // Gắn vào trái
        pnTrai.add(pnThongTin, BorderLayout.NORTH);
        pnTrai.add(scrollChiTiet, BorderLayout.CENTER);

        JPanel pnDuoiTrai = new JPanel(new BorderLayout());
        pnDuoiTrai.add(pnThanhToan, BorderLayout.CENTER);
        pnDuoiTrai.add(pnNut, BorderLayout.SOUTH);
        pnTrai.add(pnDuoiTrai, BorderLayout.SOUTH);

        // === Panel phải: MENU BAR ===
        JPanel pnPhai = new JPanel(new BorderLayout());
        pnPhai.setBorder(BorderFactory.createTitledBorder("MENU BAR"));

        String[] cols = { "Tên món", "Ảnh", "Giá" ,"Số lượng còn lại" };
        Object[][] data = {
            { "OSHI KHOAI TÂY", ".....", 25000.0 , 10},
            { "SỮA VINAMILk", ".........", 30000.0, 100 },
            { "Xà bông", ".........", 45000.0 ,20},
            { "Búa", ".........", 45000.0,49 },
            { "Dao", "...........", 36000.0 ,21},
            { "Pin", "..............", 35000.0 , 30}
        };
        JTable tblMenu = new JTable(new DefaultTableModel(data, cols));
        JScrollPane scrollMenu = new JScrollPane(tblMenu);

        JTextField tfTimKiem = new JTextField();
        JButton btnTim = new JButton("Tìm Kiếm");
        JPanel pnTimKiem = new JPanel(new BorderLayout());
        pnTimKiem.add(tfTimKiem, BorderLayout.CENTER);
        pnTimKiem.add(btnTim, BorderLayout.EAST);

        JButton btnThemSP = new JButton("Thêm sản phẩm");
        JButton btnXoaSP = new JButton("Xóa sản phẩm");
        JPanel pnThaoTac = new JPanel();
        pnThaoTac.add(btnThemSP);
        pnThaoTac.add(btnXoaSP);

        pnPhai.add(pnTimKiem, BorderLayout.NORTH);
        pnPhai.add(scrollMenu, BorderLayout.CENTER);
        pnPhai.add(pnThaoTac, BorderLayout.SOUTH);

        // Gắn vào SplitPane
        splitPane.setLeftComponent(pnTrai);
        splitPane.setRightComponent(pnPhai);

        pnTaoDon.removeAll();
        pnTaoDon.add(splitPane, BorderLayout.CENTER);
        pnTaoDon.revalidate();
        pnTaoDon.repaint();
    }
//tạo quản lí sản phẩm
    public void taoQuanLiSanPham() {
		//
		JButton bSave=new JButton("Save");
		//
		JPanel quanLiSanPHam = new JPanel();
		quanLiSanPHam.setLayout(new BorderLayout());
		quanLiSanPHam.setBorder(BorderFactory.createTitledBorder("Quản lí sản phẩm"));
		// thanh tiềm kiếm sản phẩm
		JPanel panelSearch = new JPanel();
		panelSearch.setLayout(new FlowLayout());
		panelSearch.setBorder(BorderFactory.createTitledBorder("Tìm kiếm sản phẩm"));
		JTextField boxSearch = new JTextField(20);
		panelSearch.add(boxSearch);
		JButton nutSerach = new JButton("Search");
		panelSearch.add(nutSerach);

		quanLiSanPHam.add(panelSearch, BorderLayout.NORTH);

		// bảng thông tin các loại sản phẩm toàn kho;
		JPanel panelBangThongTinSP = new JPanel();
		panelBangThongTinSP.setLayout(new BorderLayout());
		quanLiSanPHam.add(panelBangThongTinSP, BorderLayout.CENTER);
		
		JPanel panelBangThongTinSP1 = new JPanel();
		panelBangThongTinSP.setLayout(new BorderLayout());
		panelBangThongTinSP.add(panelBangThongTinSP1, BorderLayout.CENTER);
		panelBangThongTinSP.setBorder(BorderFactory.createTitledBorder("Thông tin các loại sản phẩm"));
		String[] thongtinCacCot = { "Mã Sản Phẩm", "Tên Sản Phẩm", "Số lượng Tồn Kho", "Giá Niêm Yết", "Hình ảnh" };
		String[][] data = {{"0011","Coca","1000","10000","Chưa cập nhật"}};
		//không cho chỉnh sửa cột mã 
		DefaultTableModel ttCOT = new DefaultTableModel(data,thongtinCacCot){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };
		JTable tableBangThongTinSP = new JTable(ttCOT);
		JScrollPane scrollChiTiet = new JScrollPane(tableBangThongTinSP);
		panelBangThongTinSP1.add(scrollChiTiet);
		
		
		
		
		
		
		//
		JPanel panelBangThongTinSP2 = new JPanel();
		panelBangThongTinSP2.setLayout(new FlowLayout());
		panelBangThongTinSP.add(panelBangThongTinSP2,BorderLayout.SOUTH);
		panelBangThongTinSP2.add(bSave);
		

		// bảng quản lí chức năng sản phẩm
		JPanel panelQlFunction = new JPanel();
		panelQlFunction.setLayout(new BoxLayout(panelQlFunction, BoxLayout.Y_AXIS));
		quanLiSanPHam.add(panelQlFunction, BorderLayout.WEST);
		//
		JButton add = new JButton("ADD");
		JButton remove = new JButton("Remove");
		JTextField textMaSP = new JTextField(10);
		JTextField textMaSP_Xoa = new JTextField(10);
		JTextField textTenSP = new JTextField(10);
		JTextField textSLSP = new JTextField(10);
		JTextField textGiaSP = new JTextField(10);
		JTextField textAnhSP = new JTextField(10);
		
		//
		JPanel pnADDRe1=new JPanel();
		pnADDRe1.setLayout(new GridLayout(2,1));
		pnADDRe1.setBorder(BorderFactory.createTitledBorder("Thêm Sản Phẩm"));
		panelQlFunction.add(pnADDRe1);
		
		JPanel pnADDRe11=new JPanel();
		pnADDRe11.setLayout(new GridLayout(5,2));
		pnADDRe1.add(pnADDRe11);
		pnADDRe11.add(new JLabel("Mã Sản Phẩm:"));
		pnADDRe11.add(textMaSP);
		pnADDRe11.add(new JLabel("Tên Sản Phẩm:"));
		pnADDRe11.add(textTenSP);
		pnADDRe11.add(new JLabel("Số Lượng:"));
		pnADDRe11.add(textSLSP);
		pnADDRe11.add(new JLabel("Giá Sản Phẩm:"));
		pnADDRe11.add(textGiaSP);
		pnADDRe11.add(new JLabel("Ảnh Sản Phẩm:"));
		pnADDRe11.add(textAnhSP);

		JPanel pnADDRe12=new JPanel();
		pnADDRe12.setLayout(new FlowLayout());
		pnADDRe1.add(pnADDRe12);
		pnADDRe12.add(add);
		//
		JPanel pnADDRe2=new JPanel();
		pnADDRe2.setLayout(new GridLayout(2,1));
		pnADDRe2.setBorder(BorderFactory.createTitledBorder("Xóa Sản Phẩm"));
		panelQlFunction.add(pnADDRe2);
		
		JPanel pnADDRe21=new JPanel();
		pnADDRe21.setLayout(new GridLayout(2,1));
		pnADDRe2.add(pnADDRe21);
		pnADDRe21.add(new JLabel("Mã Sản Phẩm:"));
		pnADDRe21.add(textMaSP_Xoa);
		
		JPanel pnADDRe22=new JPanel();
		pnADDRe22.setLayout(new FlowLayout());
		pnADDRe2.add(pnADDRe22);
		pnADDRe22.add(remove);
		//
		pnQuanLySanPham.removeAll();
		pnQuanLySanPham.add(quanLiSanPHam, BorderLayout.CENTER);
		pnQuanLySanPham.revalidate();
		pnQuanLySanPham.repaint();
		//
		bSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Dữ liệu đã được lưu","Thông báo",JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		
	}
    public static void main(String[] args) {
        new GiaoDienChinh();
    }
}
