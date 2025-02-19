package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit; 
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import control.FuncionarioDAO;
import model.Funcionario;
import javax.swing.LayoutStyle.ComponentPlacement; 
import javax.swing.SwingConstants; 

public class ViewInicio extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	@SuppressWarnings("unused")
	private int id ;
	private JTable tableFunc;
	private JTextField VlrPesquisar;
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewInicio frame = new ViewInicio();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewInicio() {
		setExtendedState(MAXIMIZED_BOTH);
		setTitle("Sistema Padaria");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewInicio.class.getResource("/MODIFICADO/Logo.png")));
		setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 784);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		
		JPanel JpFuncionarios = new JPanel();
	 
		tabbedPane.addTab("FUNCIONARIOS", new ImageIcon(ViewInicio.class.getResource("/ORIGINAL/funcionario.png")), JpFuncionarios, null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.menu);
		desktopPane.setBounds(0, 0, 1129, 734);
		panel.add(desktopPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setInheritsPopupMenu(true);
		
		tableFunc = new JTable();
		tableFunc.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Codigo Funcionario", "Nome", "Cargo", "RG", "CPF"
			}
		));
		scrollPane_1.setViewportView(tableFunc);
		
		JLabel BtnAdd = new JLabel("");
		BtnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ViewCadastroFuncionario frame;
				try {
					frame = new ViewCadastroFuncionario();
					desktopPane.add(frame);
					frame.setVisible(true);
					readTable();
				} catch (PropertyVetoException e1) {
				
					e1.printStackTrace();
				}				
			}
		});
		BtnAdd.setIcon(new ImageIcon(ViewInicio.class.getResource("/ORIGINAL/adicionar.png")));
		BtnAdd.setToolTipText("Adicionar");
		
		JLabel BtnDeletar = new JLabel("");
		BtnDeletar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Funcionario objFunc = new Funcionario();
				if(tableFunc.getSelectedRow() == -1 ) {
						JOptionPane.showMessageDialog(null,"Selecione um funcionario para deletar");
					}
				else {
					objFunc.setId((int) tableFunc.getValueAt(tableFunc.getSelectedRow(),0));
					try {
						ViewCadastroFuncionario frame = new ViewCadastroFuncionario();						
					frame.preencherId(objFunc); 
					frame.excluir();
					readTable();
					} catch (PropertyVetoException e1) {
						e1.printStackTrace();
					}
				}	
			}
		});
		BtnDeletar.setIcon(new ImageIcon(ViewInicio.class.getResource("/ORIGINAL/delete.png")));
		BtnDeletar.setToolTipText("Deletar");
		
		JLabel BtnPesquisar = new JLabel("");
		BtnPesquisar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Funcionario objFunc = new Funcionario();
				if(VlrPesquisar.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Digite o codigo do funcionario");
					VlrPesquisar.requestFocus();
				}
				else {
				objFunc.setId(Integer.parseInt(VlrPesquisar.getText()));
				
				ViewCadastroFuncionario frame;
				try {
					frame = new ViewCadastroFuncionario();
					desktopPane.add(frame);
					frame.preencherId(objFunc);
					frame.setVisible(true);
					frame.Pesquisar();
				readTable();
				} catch (PropertyVetoException e1) {
					
					e1.printStackTrace();
					}
				}
			}
		});
		BtnPesquisar.setIcon(new ImageIcon(ViewInicio.class.getResource("/ORIGINAL/62851-magnifying-glass-tilted-right-icon.png")));
		BtnPesquisar.setToolTipText("Pesquisar");
		
		JLabel BtnEditar = new JLabel("");
		BtnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Funcionario objFunc = new Funcionario();
				if(tableFunc.getSelectedRow() == -1 ) {
					JOptionPane.showMessageDialog(null,"Selecione um funcionario para alterar");
				}
				else {
				objFunc.setId((int) tableFunc.getValueAt(tableFunc.getSelectedRow(),0));
				
				ViewCadastroFuncionario frame;
				try {
					frame = new ViewCadastroFuncionario();
					desktopPane.add(frame);
				frame.preencherId(objFunc);
				frame.setVisible(true);
				frame.Pesquisar();
				readTable();
				} catch (PropertyVetoException e1) {
					
					e1.printStackTrace();
				}
			}
			}
		});
		BtnEditar.setIcon(new ImageIcon(ViewInicio.class.getResource("/ORIGINAL/Actions-document-edit-icon.png")));
		BtnEditar.setToolTipText("Editar");
		
		VlrPesquisar = new JTextField();
		VlrPesquisar.setHorizontalAlignment(SwingConstants.RIGHT);
		VlrPesquisar.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				readTable();
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon(ViewInicio.class.getResource("/ORIGINAL/refresh-icon.png")));
		GroupLayout gl_desktopPane = new GroupLayout(desktopPane);
		gl_desktopPane.setHorizontalGroup(
			gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_desktopPane.createSequentialGroup()
					.addGap(69)
					.addComponent(BtnAdd, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(17)
					.addComponent(BtnDeletar, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(21)
					.addComponent(BtnEditar, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(BtnPesquisar, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(VlrPesquisar, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
					.addGap(569))
				.addGroup(gl_desktopPane.createSequentialGroup()
					.addGap(26)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 1057, Short.MAX_VALUE)
					.addGap(46))
		);
		gl_desktopPane.setVerticalGroup(
			gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_desktopPane.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_desktopPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_desktopPane.createSequentialGroup()
							.addGap(17)
							.addComponent(VlrPesquisar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_desktopPane.createSequentialGroup()
							.addGap(16)
							.addComponent(lblNewLabel))
						.addComponent(BtnAdd, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(BtnDeletar, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(BtnEditar, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(BtnPesquisar))
					.addGap(32)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
					.addGap(98))
		);
		desktopPane.setLayout(gl_desktopPane);
		GroupLayout gl_JpFuncionarios = new GroupLayout(JpFuncionarios);
		gl_JpFuncionarios.setHorizontalGroup(
			gl_JpFuncionarios.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_JpFuncionarios.createSequentialGroup()
					.addGap(10)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 967, Short.MAX_VALUE))
		);
		gl_JpFuncionarios.setVerticalGroup(
			gl_JpFuncionarios.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
		);
		JpFuncionarios.setLayout(gl_JpFuncionarios);
		
		JPanel JpEstabelecimentos = new JPanel();
		tabbedPane.addTab("ESTABELECIMENTOS", new ImageIcon(ViewInicio.class.getResource("/ORIGINAL/estabelecimento.png")), JpEstabelecimentos, null);
		JpEstabelecimentos.setLayout(null);
		
		JPanel JpFornecedores = new JPanel();
		tabbedPane.addTab("FORNECEDORES", new ImageIcon(ViewInicio.class.getResource("/ORIGINAL/fornecedor.png")), JpFornecedores, null);
		JpFornecedores.setLayout(null);
		
		JPanel JpProdutos = new JPanel();
		tabbedPane.addTab("PRODUTOS", new ImageIcon(ViewInicio.class.getResource("/MODIFICADO/Ecommerce-Product-icon.png")), JpProdutos, null);
		JpProdutos.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 147, 572, 304);
		JpProdutos.add(scrollPane);
		
		JPanel JpClientes = new JPanel();
		tabbedPane.addTab("CLIENTES", new ImageIcon(ViewInicio.class.getResource("/ORIGINAL/cliente.png")), JpClientes, null);
		
		JPanel JpEstatisticas = new JPanel();
		JpEstatisticas.setBorder(null);
		tabbedPane.addTab("ESTATISTICAS", new ImageIcon(ViewInicio.class.getResource("/ORIGINAL/Statistics-icon.png")), JpEstatisticas, null);
		JpEstatisticas.setLayout(null);
		
		JPanel JpCaixa = new JPanel();
		JpCaixa.setBorder(UIManager.getBorder("CheckBox.border"));
		tabbedPane.addTab("CAIXA", new ImageIcon(ViewInicio.class.getResource("/ORIGINAL/cash-register-icon.png")), JpCaixa, null);
		JpCaixa.setLayout(null);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 1901, Short.MAX_VALUE)
					.addGap(0))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 884, Short.MAX_VALUE)
					.addGap(0))
		);
		contentPane.setLayout(gl_contentPane);
		readTable();
	}
	public void readTable() {
		DefaultTableModel modelo = (DefaultTableModel) tableFunc.getModel();
		modelo.setNumRows(0);
		FuncionarioDAO func = new FuncionarioDAO();
		
		try {
			for(Funcionario f: func.read()) {
				modelo.addRow(new Object[] {
						f.getId(),
						f.getNome(),
						f.getCargo(),
						f.getRG(),
						f.getCPF()						
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	public boolean getId() {
		Funcionario objFunc = new Funcionario();
		objFunc.setId((int) tableFunc.getValueAt(tableFunc.getSelectedRow(),0));
		id = objFunc.getId();
		return true;
	}	
}
