# テストリスト
- [x]扉作成
 - [x]３つの扉を作成、すべての扉はしまっており、未選択の状態
 - [x]３つの扉の内一つは当たりである
- [x]扉の選択
 - [x]扉の番号を指定したらその番号の扉を選択状態にする
 - [x]扉の通し番号以外のものを指定すると、IllegalArgumentExceptionを投げる
- [x]当たりではない扉を開く
 - [x]選択されていない扉を開く
 - [x]選択されていない扉の内、当たりではないものを開く
 - [x]選択された扉が存在しない場合は、IllegalStateEcxcetionを投げる
- [x]選択状態を変更する
 - [x]選択状態の変更を伝えると現在選択されていない扉でない、かつ、開かれていない方を選択状態にする
- [x]選択状態の扉を開き判定する
 - [x]当たりの判定が出たら「当たり！」に相当する文言を返す
 - [x]ハズレの判定が出たら「ハズレ」に相当する文言を返す
