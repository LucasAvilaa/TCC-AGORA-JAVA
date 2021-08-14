package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit; 
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

public class ViewCadastroProduto extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField VlrNome;
	private JTextField VlrMarca;
	private JTextField VlrNomeFornecedor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCadastroProduto frame = new ViewCadastroProduto();
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
	public ViewCadastroProduto() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewCadastroProduto.class.getResource("/MODIFICADO/Ecommerce-Product-icon.png")));
		setTitle("Cadastro Produto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Categoria:");
		lblNewLabel_1.setBounds(146, 77, 89, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Estoque Atual:");
		lblNewLabel_2.setBounds(246, 180, 122, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Descri\u00E7\u00E3o:");
		lblNewLabel_3.setBounds(27, 275, 75, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Valor Compra (uni ) :");
		lblNewLabel_4.setBounds(27, 155, 140, 14);
		contentPane.add(lblNewLabel_4);
		
		JButton button = new JButton("Salvar");
		button.setBounds(352, 430, 89, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Cancelar");
		button_1.setBounds(246, 430, 89, 23);
		contentPane.add(button_1);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(146, 24, 48, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_5 = new JLabel("Marca:");
		lblNewLabel_5.setBounds(146, 49, 89, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Valor Venda (uni):");
		lblNewLabel_6.setBounds(247, 155, 140, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Porcentagem de lucro:");
		lblNewLabel_7.setBounds(27, 184, 140, 14);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel(" Foto (opcional)");
		lblNewLabel_8.setBackground(Color.BLACK);
		lblNewLabel_8.setBounds(27, 24, 100, 120);
		contentPane.add(lblNewLabel_8);
		
		VlrNome = new JTextField();
		VlrNome.setBounds(190, 21, 251, 20);
		contentPane.add(VlrNome);
		VlrNome.setColumns(10);
		
		VlrMarca = new JTextField();
		VlrMarca.setBounds(190, 49, 251, 20);
		contentPane.add(VlrMarca);
		VlrMarca.setColumns(10);
		
		JSpinner VlrEstoqueAtual = new JSpinner();
		VlrEstoqueAtual.setBounds(370, 177, 71, 20);
		contentPane.add(VlrEstoqueAtual);
		
		JFormattedTextField VlrValorCompra = new JFormattedTextField();
		VlrValorCompra.setBounds(162, 152, 75, 20);
		contentPane.add(VlrValorCompra);
		
		JFormattedTextField VlrValorVenda = new JFormattedTextField();
		VlrValorVenda.setBounds(370, 152, 71, 20);
		contentPane.add(VlrValorVenda);
		
		JFormattedTextField VlrLucro = new JFormattedTextField();
		VlrLucro.setBounds(162, 180, 73, 20);
		contentPane.add(VlrLucro);
		
		JLabel lblNewLabel_9 = new JLabel("Limite Estoque Min:");
		lblNewLabel_9.setBounds(27, 210, 140, 14);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblLimiteEstoqueMaximo = new JLabel("Limite Estoque Max:");
		lblLimiteEstoqueMaximo.setBounds(247, 210, 140, 14);
		contentPane.add(lblLimiteEstoqueMaximo);
		
		JSpinner VlrEstoqueMin = new JSpinner();
		VlrEstoqueMin.setBounds(162, 207, 73, 20);
		contentPane.add(VlrEstoqueMin);
		
		JSpinner VlrEstoqueMax = new JSpinner();
		VlrEstoqueMax.setBounds(370, 207, 71, 20);
		contentPane.add(VlrEstoqueMax);
		
		JComboBox<String> VlrCategoria = new JComboBox<String>();
		VlrCategoria.addItem("");
		VlrCategoria.addItem("PRODUCAO");
		VlrCategoria.addItem("COPA");
		VlrCategoria.addItem("MERCEARIA");
		VlrCategoria.setBounds(218, 73, 223, 22);
		contentPane.add(VlrCategoria);
		
		JLabel lblNewLabel_10 = new JLabel("Nome Fornecedor:");
		lblNewLabel_10.setBounds(146, 114, 140, 14);
		contentPane.add(lblNewLabel_10);
		
		VlrNomeFornecedor = new JTextField();
		VlrNomeFornecedor.setToolTipText("");
		VlrNomeFornecedor.setBounds(258, 111, 183, 20);
		contentPane.add(VlrNomeFornecedor);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 300, 389, 103);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}
}
