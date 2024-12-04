import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeWithScore extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3]; // 3x3 버튼 배열
    private boolean isPlayerX = true; // true면 P1 (X 차례), false면 P2 (O 차례)
    private int p1Score = 0; // P1의 승점
    private int p2Score = 0; // P2의 승점
    private int ties = 0;    // 비김 횟수
    private JLabel scoreLabel; // 점수 표시용 라벨
    int a = 0;
    public TicTacToeWithScore() {
        // 기본 창 설정
        setTitle("Tic Tac Toe with Score");
        setSize(400, 500); // 창 크기 (점수판 추가로 높이를 키움)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 전체 레이아웃 설정 (BorderLayout)
        setLayout(new BorderLayout());

        // 게임 보드 레이아웃 (3x3 GridLayout)
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));

        // 버튼 초기화 및 추가
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton(""); // 빈 버튼 생성
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60)); // 버튼 텍스트 폰트 설정
                buttons[i][j].addActionListener(this); // 버튼에 액션 리스너 추가
                boardPanel.add(buttons[i][j]); // 버튼을 보드에 추가
            }
        }

        // 점수 표시용 라벨
        scoreLabel = new JLabel("P1 (X): 0 | P2 (O): 0 | Ties: 0", JLabel.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(scoreLabel, BorderLayout.NORTH); // 점수판을 상단에 배치

        add(boardPanel, BorderLayout.CENTER); // 게임 보드를 중앙에 배치
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 버튼 클릭 이벤트 처리
        JButton clickedButton = (JButton) e.getSource();

        // 이미 클릭된 버튼은 무시
        if (!clickedButton.getText().equals("")) {
            return;
        }

        // 현재 플레이어 표시 (X 또는 O)
        if (isPlayerX) {
            clickedButton.setText("X");
            clickedButton.setForeground(Color.RED); // X는 빨간색
        } else {
            clickedButton.setText("O");
            clickedButton.setForeground(Color.BLUE); // O는 파란색
        }

        // 턴 전환
        isPlayerX = !isPlayerX;

        // 승리 체크
        if (checkWin()) {
            if (!isPlayerX) {
                p1Score++; // P1 승리
                JOptionPane.showMessageDialog(this, "홍길동 (X)이 승리했습니다!");
            } else {
                p2Score++; // P2 승리
                JOptionPane.showMessageDialog(this, "김철수 (O)이 승리했습니다!");
            }
            updateScore();
            resetGame();
        } else if (isBoardFull()) {
            ties++; // 비김
            JOptionPane.showMessageDialog(this, "비겼습니다!");
            updateScore();
            resetGame();
        }
    }

    // 점수 업데이트
    private void updateScore() {
        scoreLabel.setText("홍길동 (X): " + p1Score + " |  김철수(O): " + p2Score + " | Ties: " + ties);
    }

    // 승리 조건 체크
    private boolean checkWin() {
        // 가로, 세로, 대각선 체크
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][1].getText().equals(buttons[i][2].getText()) &&
                    !buttons[i][0].getText().equals("")) {
                return true;
            }
            if (buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                    buttons[1][i].getText().equals(buttons[2][i].getText()) &&
                    !buttons[0][i].getText().equals("")) {
                return true;
            }
        }
        // 대각선 체크
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][2].getText()) &&
                !buttons[0][0].getText().equals("")) {
            return true;
        }
        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[1][1].getText().equals(buttons[2][0].getText()) &&
                !buttons[0][2].getText().equals("")) {
            return true;
        }
        return false;
    }

    // 보드가 가득 찼는지 체크
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    // 게임 리셋
    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        isPlayerX = true; // P1 (X)부터 시작
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToeWithScore game = new TicTacToeWithScore();
            game.setVisible(true); // 창 표시
        });
    }
}

