var boardMap = {
    '': '',
    1 : 'player1',
    2 : 'player2',
    3 : 'player3',
    4 : 'player4',
    'b': 'block',
    'p': 'powerup',
    'w': 'wall',
    'd': 'oneway_down',
    'l': 'oneway_left',
    'r': 'oneway_right',
    'u': 'oneway_up',


};
function print_board(board){
    var str = '';
    board.map(row => {
        str += '<div class="row">';
        
        row.map(cell => {
            str += `<div class="cell ${boardMap[cell]}"></div>`;
        });
        str += '</div>';
    });
    $(".board").html(str);
}