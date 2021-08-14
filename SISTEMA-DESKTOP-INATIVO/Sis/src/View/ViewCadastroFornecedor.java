package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ViewCadastroFornecedor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField VlrNome;
	private JTextField VlrNumTel;
	private JTextField VlrCPFouCNPJ;
	private JTextField VlrCEP;
	private JTextField VlrRua;
	private JTextField VlrCel;
	private JTextField VlrBairro;
	private JTextField VlrNum;
	private JTextField vlrCidade;
	private JTextField txtFornecedor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCadastroFornecedor frame = new ViewCadastroFornecedor();
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
	public ViewCadastroFornecedor() {
		setAlwaysOnTop(true);
		setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewCadastroFornecedor.class.getResource("/MODIFICADO/fornecedor.png")));
		setTitle("Cadastro Fornecedor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 508, 351);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		VlrNome = new JTextField();
		VlrNome.setBounds(118, 70, 346, 20);
		contentPane.add(VlrNome);
		VlrNome.setColumns(10);
		
		JLabel txtNome = new JLabel("Nome completo:");
		txtNome.setBounds(18, 73, 107, 14);
		contentPane.add(txtNome);
		
		JLabel txtTel = new JLabel("Tel:");
		txtTel.setBounds(291, 101, 41, 14);
		contentPane.add(txtTel);
		
		VlrNumTel = new JTextField();
		VlrNumTel.setColumns(10);
		VlrNumTel.setBounds(318, 98, 146, 20);
		contentPane.add(VlrNumTel);
		
		JLabel txtCNPJ = new JLabel("CNPJ:");
		txtCNPJ.setBounds(18, 98, 96, 14);
		contentPane.add(txtCNPJ);
		
		JLabel txtCEP = new JLabel("CEP:\r\n\r\n");
		txtCEP.setBounds(202, 126, 48, 14);
		contentPane.add(txtCEP);
		
		JLabel txtRua = new JLabel("Rua:");
		txtRua.setBounds(18, 154, 48, 14);
		contentPane.add(txtRua);
		
		VlrCPFouCNPJ = new JTextField();
		VlrCPFouCNPJ.setBounds(65, 98, 206, 20);
		contentPane.add(VlrCPFouCNPJ);
		VlrCPFouCNPJ.setColumns(10);
		
		VlrCEP = new JTextField();
		VlrCEP.setBounds(239, 123, 120, 20);
		contentPane.add(VlrCEP);
		VlrCEP.setColumns(10);
		
		VlrRua = new JTextField();
		VlrRua.setEditable(false);
		VlrRua.setBounds(54, 151, 289, 20);
		contentPane.add(VlrRua);
		VlrRua.setColumns(10);
		
		JLabel txtCel = new JLabel("Cel:");
		txtCel.setBounds(18, 129, 48, 14);
		contentPane.add(txtCel);
		
		VlrCel = new JTextField();
		VlrCel.setBounds(50, 123, 142, 20);
		contentPane.add(VlrCel);
		VlrCel.setColumns(10);
		
		VlrBairro = new JTextField();
		VlrBairro.setEditable(false);
		VlrBairro.setBounds(65, 179, 196, 20);
		contentPane.add(VlrBairro);
		VlrBairro.setColumns(10);
		
		JLabel txtBairro = new JLabel("Bairro:");
		txtBairro.setBounds(18, 182, 48, 14);
		contentPane.add(txtBairro);
		
		JLabel txtNum = new JLabel("Num:");
		txtNum.setBounds(369, 126, 48, 14);
		contentPane.add(txtNum);
		
		VlrNum = new JTextField();
		VlrNum.setToolTipText("");
		VlrNum.setBounds(408, 123, 56, 20);
		contentPane.add(VlrNum);
		VlrNum.setColumns(10);
		
		JLabel txtEstado = new JLabel("Estado:");
		txtEstado.setBounds(358, 155, 48, 14);
		contentPane.add(txtEstado);
		
		JComboBox<String> VlrEstado = new JComboBox<String>();
		VlrEstado.setBounds(416, 151, 48, 22);
		contentPane.add(VlrEstado);
		
		JLabel txtTipoPessoa = new JLabel("Tipo Pessoa:");
		txtTipoPessoa.setBounds(18, 214, 102, 14);
		contentPane.add(txtTipoPessoa);
		
		JLabel txtInfPessoal = new JLabel("Informa\u00E7\u00F5es pessoais");
		txtInfPessoal.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtInfPessoal.setBounds(18, 29, 165, 14);
		contentPane.add(txtInfPessoal);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(176, 42, 295, 9);
		contentPane.add(separator);
		
		JLabel txtCidade = new JLabel("Cidade:");
		txtCidade.setBounds(291, 182, 72, 14);
		contentPane.add(txtCidade);
		
		vlrCidade = new JTextField();
		vlrCidade.setEditable(false);
		vlrCidade.setBounds(346, 179, 118, 20);
		contentPane.add(vlrCidade);
		vlrCidade.setColumns(10);
		
		txtFornecedor = new JTextField();
		txtFornecedor.setEditable(false);
		txtFornecedor.setHorizontalAlignment(SwingConstants.CENTER);
		txtFornecedor.setText("FORNECEDOR");
		txtFornecedor.setBounds(94, 211, 118, 20);
		contentPane.add(txtFornecedor);
		txtFornecedor.setColumns(10);
		
		JLabel COMENTARIO = new JLabel("ADICIONAR QUAL PRODUTO QUE ELE PRODUZ?");
		COMENTARIO.setBounds(153, 242, 311, 14);
		contentPane.add(COMENTARIO);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(375, 267, 89, 23);
		contentPane.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(269, 267, 89, 23);
		contentPane.add(btnCancelar);
	}
}
