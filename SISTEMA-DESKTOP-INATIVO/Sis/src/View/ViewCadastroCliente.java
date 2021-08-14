package View;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JComboBox; 
 import javax.swing.text.MaskFormatter;
import br.com.parg.viacep.ViaCEP;
import br.com.parg.viacep.ViaCEPException; 
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;

public class ViewCadastroCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField VlrNome;
	private JTextField VlrRua;
	private JTextField VlrBairro;
	private JTextField VlrNum;
	private JTextField VlrUsuario;
	private JTextField VlrCidade;
	private JPasswordField VlrSenha;
	private JTextField VlrEstado;
	private JPasswordField VlrConfirmaSenha;
	private JFormattedTextField VlrCPF;
	private JFormattedTextField VlrCEP;
	private JFormattedTextField VlrRg;
	private JFormattedTextField VlrDtNasc;
	private JFormattedTextField VlrCel; 
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCadastroCliente frame = new ViewCadastroCliente();
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
	public ViewCadastroCliente() {
		 
		setAlwaysOnTop(true);
		setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewCadastroCliente.class.getResource("/ORIGINAL/cliente.png")));
		setTitle("Cadastro Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 569, 560);
		contentPane = new JPanel();
		setContentPane(contentPane);
		
		VlrNome = new JTextField();
		VlrNome.setBounds(118, 118, 414, 20);
		VlrNome.addFocusListener(new FocusAdapter() { 
			public void focusLost(FocusEvent e) {
				String NomeMaior = VlrNome.getText().toUpperCase();
				VlrNome.setText(NomeMaior);
				}
		});
		VlrNome.setColumns(10);
		
		
		JLabel txtNome = new JLabel("Nome completo:");
		txtNome.setBounds(18, 121, 107, 14);
		
		JLabel txtDataNasc = new JLabel("Dt Nascimento:");
		txtDataNasc.setBounds(317, 149, 107, 14);
		
		JLabel txtCPF = new JLabel("CPF:");
		txtCPF.setBounds(18, 149, 41, 14);
		
		JLabel txtCEP = new JLabel("CEP:\r\n\r\n");
		txtCEP.setBounds(18, 208, 48, 14);
		
		JLabel txtRua = new JLabel("Rua:");
		txtRua.setBounds(184, 208, 48, 14);
		
		VlrRua = new JTextField();
		VlrRua.setBounds(220, 205, 312, 20);
		VlrRua.setEditable(false);
		VlrRua.setColumns(10);
		
		
		VlrBairro = new JTextField();
		VlrBairro.setBounds(65, 233, 242, 20);
		VlrBairro.setEditable(false);
		VlrBairro.setColumns(10);
		
		JLabel txtBairro = new JLabel("Bairro:");
		txtBairro.setBounds(18, 236, 48, 14);
		
		JLabel txtNum = new JLabel("Num:");
		txtNum.setBounds(317, 236, 48, 14);
		
		VlrNum = new JTextField();
		VlrNum.setBounds(350, 233, 56, 20);
		VlrNum.setToolTipText("");
		VlrNum.setColumns(10);
		
		JLabel txtEstado = new JLabel("Estado:");
		txtEstado.setBounds(409, 236, 48, 14);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(429, 472, 89, 23);
		btnSalvar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {				 
					try {
					
					if(validarCampos()) {
						if(preencherCampos()) {
							 
							//	if(DAOLogin.incluir(objLogin)) {
									JOptionPane.showMessageDialog(null,"Dados salvo com sucesso!");
								//			}							
									}
						}
						else {
						JOptionPane.showMessageDialog(null,"Nao foi possivel adicionar o registro");
							 }
					}
				catch(Exception erro) {
					JOptionPane.showMessageDialog(null, "Erro"+ erro.getMessage());
				}}
		})
			
		;
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(76, 472, 89, 23);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();			}
		});
		
		JLabel txtSexo = new JLabel("Sexo:");
		txtSexo.setBounds(386, 177, 48, 14);
		
		JComboBox<String> VlrSexo = new JComboBox<String>();
		VlrSexo.setBounds(432, 174, 102, 22);
		VlrSexo.setMaximumRowCount(2);
	 	
		JLabel txtInfPessoal = new JLabel("Informa\u00E7\u00F5es pessoais");
		txtInfPessoal.setBounds(18, 77, 165, 14);
		txtInfPessoal.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(176, 90, 362, 9);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(23, 311, 63, 26);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(90, 328, 443, 9);
		
		JLabel txtUsuario = new JLabel("Usuario:");
		txtUsuario.setBounds(23, 372, 86, 14);
		
		JLabel txtSenha = new JLabel("Senha:");
		txtSenha.setBounds(23, 400, 48, 14);
		
		VlrUsuario = new JTextField();
		VlrUsuario.setBounds(133, 365, 226, 20);
		VlrUsuario.setColumns(10);
		
		JLabel txtCidade = new JLabel("Cidade:");
		txtCidade.setBounds(18, 266, 58, 14);
		
		VlrCidade = new JTextField();
		VlrCidade.setBounds(80, 264, 452, 20);
		VlrCidade.setEditable(false);
		VlrCidade.setColumns(10);
		
		VlrSenha = new JPasswordField();
		VlrSenha.setBounds(133, 393, 226, 20);
		
		VlrEstado = new JTextField();
		VlrEstado.setBounds(460, 233, 72, 20);
		VlrEstado.setEditable(false);
		VlrEstado.setColumns(10);
		
		VlrCPF = new JFormattedTextField();
		VlrCPF.setBounds(69, 146, 226, 20);
		MaskFormatter maskCPF = null;
		
		try {
			maskCPF= new MaskFormatter("###.###.###-##");
		}catch(ParseException e2){
			e2.printStackTrace();
		}maskCPF.install(VlrCPF);
		
		VlrDtNasc = new JFormattedTextField();
		VlrDtNasc.setBounds(426, 146, 106, 20);
		VlrDtNasc.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		MaskFormatter maskData = null;
		try {
			maskData = new MaskFormatter("##/##/####");
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		maskData.install(VlrDtNasc);				
		
		 VlrCel = new JFormattedTextField();
		 VlrCel.setBounds(220, 174, 156, 20);
		
		MaskFormatter maskCel = null;
		
		try {
			maskCel = new MaskFormatter("(##)#####-####");			
		}catch(ParseException e2){
			e2.printStackTrace();
		}maskCel.install(VlrCel);
		
		
		
		 VlrCEP = new JFormattedTextField();
		 VlrCEP.setBounds(57, 205, 117, 20);
		
		MaskFormatter maskCEP = null;
		
		try {
			maskCEP = new MaskFormatter("#####-###");			
		}catch(ParseException e2){
			e2.printStackTrace();
		}maskCEP.install(VlrCEP);
		
		VlrCEP.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				ViaCEP viaCEP = new ViaCEP();
					
				try {
					viaCEP.buscar(VlrCEP.getText());				 
					VlrRua.setText(viaCEP.getLogradouro());
					VlrCidade.setText(viaCEP.getLocalidade());
					VlrEstado.setText(viaCEP.getUf());
					VlrBairro.setText(viaCEP.getBairro());
						
				} catch (ViaCEPException e1) {				
					JOptionPane.showMessageDialog(null, "CEP não encontrado!");
					VlrCEP.setText("");
				} 
			}
		});
		
		JLabel txtCel = new JLabel("Cel:");
		txtCel.setBounds(183, 177, 41, 14);
		
		JLabel txtConfirmaSenha = new JLabel("Confirmar Senha:");
		txtConfirmaSenha.setBounds(23, 426, 118, 14);
		
		VlrConfirmaSenha = new JPasswordField();
		VlrConfirmaSenha.setBounds(133, 419, 226, 20);
		VlrConfirmaSenha.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
			}
		});
		
		JLabel txtRg = new JLabel("RG:");
		txtRg.setBounds(18, 177, 48, 14);
		
		VlrRg = new JFormattedTextField();
		VlrRg.setBounds(46, 174, 127, 20);
		MaskFormatter maskRg= null;
		 
		
		try {
			maskRg= new MaskFormatter("##.###.###-#");
		}catch(ParseException e2) {
			e2.printStackTrace();
		
		}maskRg.install(VlrRg);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(201, 472, 87, 23);

		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(319, 472, 92, 23);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
			}
		});
		
		JLabel lblNewLabel = new JLabel("Codigo Cliente");
		lblNewLabel.setBounds(18, 30, 185, 17);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textField = new JTextField();
		textField.setBounds(207, 27, 136, 23);
		textField.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField.setText("000000000");
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setEditable(false);
		textField.setColumns(10);
		contentPane.setLayout(null);
		contentPane.add(btnCancelar);
		contentPane.add(btnExcluir);
		contentPane.add(btnEditar);
		contentPane.add(btnSalvar);
		contentPane.add(lblNewLabel);
		contentPane.add(textField);
		contentPane.add(separator);
		contentPane.add(txtInfPessoal);
		contentPane.add(VlrNome);
		contentPane.add(txtNome);
		contentPane.add(txtCPF);
		contentPane.add(VlrCPF);
		contentPane.add(txtDataNasc);
		contentPane.add(VlrDtNasc);
		contentPane.add(VlrRg);
		contentPane.add(txtRg);
		contentPane.add(txtCel);
		contentPane.add(VlrCel);
		contentPane.add(txtSexo);
		contentPane.add(VlrSexo);
		contentPane.add(txtCEP);
		contentPane.add(VlrCEP);
		contentPane.add(txtRua);
		contentPane.add(VlrRua);
		contentPane.add(VlrConfirmaSenha);
		contentPane.add(txtConfirmaSenha);
		contentPane.add(txtUsuario);
		contentPane.add(VlrUsuario);
		contentPane.add(txtSenha);
		contentPane.add(VlrSenha);
		contentPane.add(lblLogin);
		contentPane.add(separator_1);
		contentPane.add(txtCidade);
		contentPane.add(VlrCidade);
		contentPane.add(VlrBairro);
		contentPane.add(txtBairro);
		contentPane.add(txtNum);
		contentPane.add(VlrNum);
		contentPane.add(txtEstado);
		contentPane.add(VlrEstado);
		}
	
	public boolean validarCampos() {
		if(VlrNome.getText().equals("")) {
			JOptionPane.showMessageDialog(this,"Preencha todos os campos!");
			return false;
		}					
		return true;
	}
	
	 public boolean preencherCampos() {

	//	 objFunc = new DAOFunc();
	//	 objFunc.setNome(VlrNome.getText());
	//	 objFunc.setCPF(VlrCPF.getText());
	//	 objFunc.setRG(VlrRg.getText());
	//	 objFunc.setDtNascimento(VlrDtNasc.getText());
	//	 objFunc.setCel(VlrCel.getText());
	//	 objFunc.setSexo(VlrSexo.getText());
	//	 objFunc.setCEP(Integer.parseInt(VlrCEP.getText()));
	//	 objFunc.setRua(VlrRua.getText());
	//	 objFunc.setBairro(VlrBairro.getText());
	//	 objFunc.setNumero(Integer.parseInt(VlrNum.getText()));
	//	 objFunc.setEstado(VlrEstado.getText());
	//	 objFunc.setCidade(VlrCidade.getText());
	// 	 objFunc.setCargo(VlrCargo.getSelectedItem());
	//	 objFunc.setUsuario(VlrUsuario.getText());
	//	 objFunc.setSenha(VlrSenha.getText());	 
 
		 return true;
	 }
	 
	 public boolean preencherCamposLogin() {
 
			return true;
		}
}
