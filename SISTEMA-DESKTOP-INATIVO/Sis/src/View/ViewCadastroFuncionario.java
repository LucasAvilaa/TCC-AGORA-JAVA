package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyVetoException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import br.com.parg.viacep.ViaCEP;
import br.com.parg.viacep.ViaCEPException;
import control.FuncionarioDAO; 
import model.Funcionario; 

public class ViewCadastroFuncionario extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField VlrNome;
	private JTextField VlrRua;
	private JTextField VlrBairro;
	private JTextField VlrNum;
	private JTextField VlrUsuario;
	private JTextField VlrCidade;
	private JPasswordField VlrSenha;
	private JTextField VlrUF;
	private JPasswordField VlrConfirmaSenha;
	private JFormattedTextField VlrCPF;
	private JFormattedTextField VlrCEP;
	private JFormattedTextField VlrRg;
	private JFormattedTextField VlrDtNasc;
	private JFormattedTextField VlrCel;
	private String ValorSexo;
	private String Cargo; 
	private Funcionario objFunc;
	private FuncionarioDAO DAO; 
	private JTextField VlrId; 
	private int controle = 0;
 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
						
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCadastroFuncionario frame = new ViewCadastroFuncionario();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}			
		});	
	}	
	/**
	 * 
	 * Create the frame.
	 * @tClosable(true);
		sethrows PropertyVetoExcic 
		)(*/
	ViewCadastroFuncionario() throws PropertyVetoException {
		setSelected(true);
		setClosed(true);
		setIcon(true);
		setFrameIcon(new ImageIcon(ViewCadastroFuncionario.class.getResource("/MODIFICADO/funcionario.png")));
		setClosable(true);
		setMaximum(true);
		setMinimumSize(new Dimension(620, 620));
		DAO = new FuncionarioDAO(); 
		controle = 0;  
		setBackground(Color.WHITE);
		setTitle("Cadastro Funcionario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1134, 621);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Tahoma", Font.BOLD, 21));
		setContentPane(contentPane);
		
		VlrNome = new JTextField();
		VlrNome.setBounds(387, 128, 414, 20);
		VlrNome.addFocusListener(new FocusAdapter() { 
			public void focusLost(FocusEvent e) {
				String NomeMaior = VlrNome.getText().toUpperCase();
				VlrNome.setText(NomeMaior);
				}
		});
		VlrNome.setColumns(10);
		
		JLabel txtNome = new JLabel("Nome completo:");
		txtNome.setBounds(287, 131, 107, 14);
		
		JLabel txtDataNasc = new JLabel("Dt Nascimento:");
		txtDataNasc.setBounds(586, 159, 107, 14);
		
		JLabel txtCPF = new JLabel("CPF:");
		txtCPF.setBounds(287, 159, 41, 14);
		
		JLabel txtCEP = new JLabel("CEP:");
		txtCEP.setBounds(287, 240, 48, 14);
		
		JLabel txtRua = new JLabel("Rua:");
		txtRua.setBounds(453, 240, 48, 14);
		
		VlrRua = new JTextField();
		VlrRua.setBounds(489, 237, 312, 20);
		VlrRua.setEditable(false);
		VlrRua.setColumns(10);
		
		VlrBairro = new JTextField();
		VlrBairro.setBounds(334, 265, 242, 20);
		VlrBairro.setEditable(false);
		VlrBairro.setColumns(10);
		
		JLabel txtBairro = new JLabel("Bairro:");
		txtBairro.setBounds(287, 268, 48, 14);
		
		JLabel txtNum = new JLabel("Num:");
		txtNum.setBounds(586, 268, 48, 14);
		
		VlrNum = new JTextField();
		VlrNum.setBounds(619, 265, 56, 20);
		VlrNum.setToolTipText("");
		VlrNum.setColumns(10);
		
		JLabel txtEstado = new JLabel("Estado:");
		txtEstado.setBounds(678, 268, 48, 14);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(733, 528, 89, 23);
		btnSalvar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {						
					try {				 		
				 			if(controle ==0) {
				 				if(validarCampos()) {
				 				if(preencherInformacoes()) {
				 					if(DAO.incluir(objFunc)) {				 				
				 						JOptionPane.showMessageDialog(null,"Dados salvo com sucesso!");		 						 
				 					}
				 				}
				 			}
				 else {
						JOptionPane.showMessageDialog(null,"Nao foi possivel adicionar o registro");
						 }
				 	}			
				 		if(controle==1) {
				 			if(validarCampos()) {
				 				if(preencherInformacoes2()) {
				 					if(DAO.alterar(objFunc)) {				 				
				 						JOptionPane.showMessageDialog(null,"Dados alterado com sucesso!");
				 					 	dispose();
				 						ViewCadastroFuncionario tela = new ViewCadastroFuncionario();
				 						tela.setVisible(true);
				 					//	inicio.atualizar();
				 					}
				 				}
				 			}	
				 			
				 		else {
							JOptionPane.showMessageDialog(null,"Nao foi possivel alterar o registro");
							 }
				 		}	
				 		
				 	}
				 	catch(Exception erro) {
				 		JOptionPane.showMessageDialog(null, "Erro: " + erro.getMessage());
				 	}
			}			
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(621, 528, 89, 23);
		
		JLabel txtSexo = new JLabel("Sexo:");
		txtSexo.setBounds(644, 213, 48, 14);
		
		JComboBox<String> VlrSexo = new JComboBox<String>();
		VlrSexo.setBounds(695, 209, 106, 22);
		VlrSexo.addItem("Selecione");
		VlrSexo.addItem("Masculino");
		VlrSexo.addItem("Feminino"); 
				
		VlrSexo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ValorSexo = VlrSexo.getSelectedItem().toString();
				 
					if(VlrSexo.getSelectedItem().toString() == "Masculino" ) {
						 ValorSexo = "M";
					}else {
						ValorSexo = "F";
					}					 
			}
		});
		
		JLabel txtUsuario = new JLabel("Usuario:");
		txtUsuario.setBounds(292, 404, 86, 14);
		
		JLabel txtSenha = new JLabel("Senha:");
		txtSenha.setBounds(292, 432, 48, 14);
		
		VlrUsuario = new JTextField();
		VlrUsuario.setBounds(402, 397, 226, 20);
		VlrUsuario.setColumns(10);
		
		JLabel txtCidade = new JLabel("Cidade:");
		txtCidade.setBounds(287, 298, 58, 14);
		
		VlrCidade = new JTextField();
		VlrCidade.setBounds(349, 296, 452, 20);
		VlrCidade.setEditable(false);
		VlrCidade.setColumns(10);
		
		VlrSenha = new JPasswordField();
		VlrSenha.setBounds(402, 425, 226, 20);
		
		JLabel txtCargo = new JLabel("Cargo:");
		txtCargo.setBounds(638, 432, 48, 14);
		
		JComboBox<String> VlrCargo = new JComboBox<String>();
		VlrCargo.setBounds(695, 426, 107, 22);
		VlrCargo.addItem("Selecione");
		VlrCargo.addItem("Administrador");
		VlrCargo.addItem("Caixa");
		VlrCargo.addItem("Balconista");
		VlrCargo.addItem("Garcom");
		
		VlrCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cargo = VlrCargo.getSelectedItem().toString();				 
			}
		});		
		
		VlrUF = new JTextField();
		VlrUF.setBounds(729, 265, 72, 20);
		VlrUF.setEditable(false);
		VlrUF.setColumns(10);
		
		VlrCPF = new JFormattedTextField();
		VlrCPF.setBounds(338, 156, 226, 20);
		
MaskFormatter maskCPF = null;
		
		try {
			maskCPF= new MaskFormatter("###.###.###-##");
		}catch(ParseException e2){
			e2.printStackTrace();
		}maskCPF.install(VlrCPF);
		
		VlrDtNasc = new JFormattedTextField();
		VlrDtNasc.setBounds(695, 156, 106, 20);
		VlrDtNasc.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		MaskFormatter maskData = null;
		try {
			maskData = new MaskFormatter("##/##/####");
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		maskData.install(VlrDtNasc);
		VlrDtNasc.getText().replaceAll("/", "");
		
		 VlrCel = new JFormattedTextField();
		 VlrCel.setBounds(332, 209, 303, 20);
		
		MaskFormatter maskCel = null;
		
		try {
			maskCel = new MaskFormatter("(##)#####-####");			
		}catch(ParseException e2){
			e2.printStackTrace();
		}maskCel.install(VlrCel);		
		
		 VlrCEP = new JFormattedTextField();
		 VlrCEP.setBounds(326, 237, 117, 20);
		
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
					VlrUF.setText(viaCEP.getUf());
					VlrBairro.setText(viaCEP.getBairro());
						
				} catch (ViaCEPException e1) {				
					JOptionPane.showMessageDialog(null, "CEP n�o encontrado!");
					VlrCEP.setText("");
				} 
			}
		});
		
		JLabel txtCel = new JLabel("Cel:");
		txtCel.setBounds(287, 212, 41, 14);
		
		JLabel txtConfirmaSenha = new JLabel("Confirmar Senha:");
		txtConfirmaSenha.setBounds(292, 458, 118, 14);
		
		VlrConfirmaSenha = new JPasswordField();
		VlrConfirmaSenha.setBounds(402, 451, 226, 20);
	
		JLabel txtRg = new JLabel("RG:");
		txtRg.setBounds(287, 184, 48, 14);
		
		VlrRg = new JFormattedTextField();
		VlrRg.setBounds(323, 181, 478, 20);
		MaskFormatter maskRg= null;
		 
		
		try {
			maskRg= new MaskFormatter("##.###.###-#");
		}catch(ParseException e2) {
			e2.printStackTrace();
		
		}maskRg.install(VlrRg);
		
		VlrId = new JTextField();
		VlrId.setBounds(473, 25, 184, 20);
		VlrId.setColumns(10);
				
		JButton btnPesquisar = new JButton("pesquisar");
		btnPesquisar.setBounds(679, 24, 107, 23);
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			try {	
				if(VlrId.getText().equals("")) {
						JOptionPane.showMessageDialog(null,"Preencha o campo Codigo do funcionario");
						VlrId.requestFocus();
				}
					Funcionario objFunc = DAO.pesquisar(Integer.parseInt(VlrId.getText()));
								
				 if(objFunc == null) {
						JOptionPane.showMessageDialog(null,"N�o encontrado");
				
				}else {
					if(controle == 1) {
						
					VlrId.setEditable(false);
					VlrNome.setEditable(true);
					VlrCPF.setEditable(true);
					VlrRg.setEditable(true); 
					VlrDtNasc.setEditable(true);
					VlrCEP.setEditable(true);
					VlrUsuario.setEditable(true);
					VlrSenha.setEditable(true);
					VlrConfirmaSenha.setEditable(true);
					VlrCel.setEditable(true);			 
					VlrNum.setEditable(true);
					} 
					
					VlrNome.setText(objFunc.getNome());
					VlrCPF.setText(objFunc.getCPF());
					VlrRg.setText(objFunc.getRG());
			//		VlrCargo.setText(objFunc.getCargo());
					VlrDtNasc.setText(objFunc.getDtNasc().toString());
			//		VlrSexo.setText(objFunc.getSexo());
					VlrCel.setText(objFunc.getCel());
					VlrUsuario.setText(objFunc.getUsuario());
					VlrSenha.setText(objFunc.getSenha());
					VlrConfirmaSenha.setText(objFunc.getSenha());
					VlrCEP.setText(objFunc.getCEP());
					VlrRua.setText(objFunc.getRua()); 
					VlrBairro.setText(objFunc.getBairro());
					VlrNum.setText(Integer.toString(objFunc.getNum()));
					VlrUF.setText(objFunc.getUF());
					VlrRg.setText(objFunc.getRG()); 
					VlrCidade.setText(objFunc.getCidade()); 
				}
			}
			catch(Exception erro) {
				System.out.println(erro.getMessage());
			}
			}});
		
		JLabel lblCodigoDoFuncionario = new JLabel("Codigo do Funcionario");
		lblCodigoDoFuncionario.setBounds(293, 25, 189, 20);
		lblCodigoDoFuncionario.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1118, 62);
		panel.setBackground(Color.WHITE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(252, 88, 578, 250);
		panel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_1.setForeground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es Pessoais", TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(252, 359, 578, 141);
		panel_2.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));
		
		
		VlrId.setEditable(false);
		contentPane.setLayout(null);
		contentPane.add(btnCancelar);
		contentPane.add(btnSalvar);
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
		contentPane.add(txtCargo);
		contentPane.add(VlrCargo);
		contentPane.add(txtCidade);
		contentPane.add(VlrCidade);
		contentPane.add(VlrBairro);
		contentPane.add(txtBairro);
		contentPane.add(txtNum);
		contentPane.add(VlrNum);
		contentPane.add(txtEstado);
		contentPane.add(VlrUF);
		contentPane.add(VlrId);
		contentPane.add(btnPesquisar);
		contentPane.add(lblCodigoDoFuncionario);
		contentPane.add(panel);
		contentPane.add(panel_1);
		contentPane.add(panel_2);
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(controle !=0) {
				VlrId.setEditable(false);
				VlrNome.setEditable(true);
				VlrCPF.setEditable(true);
				VlrRg.setEditable(true); 
				VlrDtNasc.setEditable(true);
				VlrCEP.setEditable(true);
				VlrUsuario.setEditable(true);
				VlrSenha.setEditable(true);
				VlrConfirmaSenha.setEditable(true);
				VlrCel.setEditable(true);			 
				VlrNum.setEditable(true);  
				controle = 0;	
				limpar();
				}
				else {					
					dispose();					
				   }
				}
		});	
	}
		
	@SuppressWarnings("deprecation")
	public boolean validarCampos() {					
		if(VlrNome.getText().equals("")) {
			JOptionPane.showMessageDialog(null,"Preencha o campo nome!");
			VlrNome.requestFocus();
			return false;
		}
		else if(VlrCPF.getText().equals("   .   .   -  ")) {
			JOptionPane.showMessageDialog(null,"Preencha o campo CPF!");
			VlrCPF.requestFocus();
			return false;
		}		
		else if(VlrRg.getText().equals("  .   .   - ")) {
			JOptionPane.showMessageDialog(null,"Preencha o campo RG!");
			VlrRg.requestFocus();
			return false;
		}		
		else if(VlrDtNasc.getText().equals("  /  /    ")) {
			JOptionPane.showMessageDialog(null,"Preencha o campo Data Nascimento!");
			VlrDtNasc.requestFocus();
			return false;
		}	
		else if(VlrCel.getText().equals("")) {
			JOptionPane.showMessageDialog(null,"Preencha o campo Celular!");
			VlrCel.requestFocus();
			return false;
		}
		else if(VlrCEP.getText().equals("     -   ")) {
			JOptionPane.showMessageDialog(null,"Preencha o campo CEP!");
			VlrCEP.requestFocus();
			return false;
		}
		else if(VlrNum.getText().equals("")) {
			JOptionPane.showMessageDialog(null,"Preencha o campo Numero!");
			VlrNum.requestFocus();
			return false;
		}		
		else if(VlrUsuario.getText().equals("")) {
			JOptionPane.showMessageDialog(null,"Preencha o campo Usuario!");
			VlrUsuario.requestFocus();
			return false;
		}		
		else if(VlrSenha.getText().equals("")) {
			JOptionPane.showMessageDialog(null,"Preencha o campo Senha!");
			VlrSenha.requestFocus();
			return false;
		}
		if(VlrConfirmaSenha.getText().equals(VlrSenha.getText())) {	
		}
			else{
				JOptionPane.showMessageDialog(null, "Senhas nao conferem");
				VlrConfirmaSenha.requestFocus();
				return false;						
		}			
		return true;
	}	 	

	public void limpar(){
		VlrId.setText("");
		VlrNome.setText("");
		VlrCPF.setText("");
		VlrRg.setText(""); 
		VlrDtNasc.setText("");
		VlrCEP.setText("");
		VlrUsuario.setText("");
		VlrSenha.setText("");
		VlrConfirmaSenha.setText("");
		VlrCel.setText("");
		VlrRua.setText("");
		VlrBairro.setText("");
		VlrCidade.setText("");
		VlrNum.setText("");
		VlrUF.setText("");		
	}
	
	public boolean preencherInformacoes() throws ParseException {		 		 
		objFunc = new Funcionario();
 	
	 	java.util.Date date = new SimpleDateFormat("dd/MM/yy").parse(VlrDtNasc.getText()); 	
	 	new java.sql.Date(date.getTime());
	 	
		Random random = new Random();
		int id	= random.nextInt(100000);	
		  
			 objFunc.setId(id);			 
			 objFunc.setNome(VlrNome.getText());
			 objFunc.setCPF(VlrCPF.getText());
			 objFunc.setRG(VlrRg.getText());
			 objFunc.setDtNasc(new java.sql.Date(date.getTime()));	 
			 objFunc.setSexo(ValorSexo);
			 objFunc.setCargo(Cargo);			 
			 objFunc.setCel(VlrCel.getText());
			 objFunc.setUsuario(VlrUsuario.getText());
			 objFunc.setSenha(VlrSenha.getPassword().toString());
			 objFunc.setCEP(VlrCEP.getText());
			 objFunc.setRua(VlrRua.getText());
			 objFunc.setBairro(VlrBairro.getText());
			 objFunc.setNum(Integer.parseInt(VlrNum.getText()));
			 objFunc.setUF(VlrUF.getText());
			 objFunc.setCidade(VlrCidade.getText());			 	
		 return true;
	 }
	
	public boolean preencherInformacoes2() throws ParseException {		 		 
		objFunc = new Funcionario();
 	
	 	java.util.Date date = new SimpleDateFormat("dd/MM/yy").parse(VlrDtNasc.getText()); 	
	 	new java.sql.Date(date.getTime());	 	
	 
			 objFunc.setId(Integer.parseInt(VlrId.getText()));			 
			 objFunc.setNome(VlrNome.getText());
			 objFunc.setCPF(VlrCPF.getText());
			 objFunc.setRG(VlrRg.getText());
			 objFunc.setDtNasc(new java.sql.Date(date.getTime()));	 
			 objFunc.setSexo(ValorSexo);
			 objFunc.setCargo(Cargo);			 
			 objFunc.setCel(VlrCel.getText());
			 objFunc.setUsuario(VlrUsuario.getText());
			 objFunc.setSenha(VlrSenha.getPassword().toString());
			 objFunc.setCEP(VlrCEP.getText());
			 objFunc.setRua(VlrRua.getText());
			 objFunc.setBairro(VlrBairro.getText());
			 objFunc.setNum(Integer.parseInt(VlrNum.getText()));
			 objFunc.setUF(VlrUF.getText());
			 objFunc.setCidade(VlrCidade.getText());			 	
		 return true;
	 }
	
	public void preencherId(Funcionario objFunc) {
		VlrId.setText(Integer.toString(objFunc.getId()));
		controle=1;
	}
 
	public void Pesquisar() {
		try {
		Funcionario objFunc = DAO.pesquisar(Integer.parseInt(VlrId.getText()));
		 if(objFunc == null) {
				JOptionPane.showMessageDialog(null,"N�o encontrado");
		
		}else {
			
			VlrId.setEditable(false);
			VlrNome.setEditable(true);
			VlrCPF.setEditable(true);
			VlrRg.setEditable(true); 
			VlrDtNasc.setEditable(true);
			VlrCEP.setEditable(true);
			VlrUsuario.setEditable(true);
			VlrSenha.setEditable(true);
			VlrConfirmaSenha.setEditable(true);
			VlrCel.setEditable(true);			 
			VlrNum.setEditable(true);
			} 
			
			VlrNome.setText(objFunc.getNome());
			VlrCPF.setText(objFunc.getCPF());
			VlrRg.setText(objFunc.getRG());
	//		VlrCargo.setText(objFunc.getCargo());
			VlrDtNasc.setText(objFunc.getDtNasc().toString());
	//		VlrSexo.setText(objFunc.getSexo());
			VlrCel.setText(objFunc.getCel());
			VlrUsuario.setText(objFunc.getUsuario());
			VlrSenha.setText(objFunc.getSenha());
			VlrConfirmaSenha.setText(objFunc.getSenha());
			VlrCEP.setText(objFunc.getCEP());
			VlrRua.setText(objFunc.getRua()); 
			VlrBairro.setText(objFunc.getBairro());
			VlrNum.setText(Integer.toString(objFunc.getNum()));
			VlrUF.setText(objFunc.getUF());
			VlrRg.setText(objFunc.getRG()); 
			VlrCidade.setText(objFunc.getCidade()); 
		}
	catch(Exception e) {
		System.out.println(e.getMessage());
		}
	}	
	public void excluir() {
		FuncionarioDAO DAO = new FuncionarioDAO();
		 try {
			DAO.excluir(Integer.parseInt(VlrId.getText()));
		JOptionPane.showMessageDialog(null,"Dados excluidos com sucesso");
		} catch (Exception e) {			
			e.printStackTrace();
		}
		 			
	}
}
