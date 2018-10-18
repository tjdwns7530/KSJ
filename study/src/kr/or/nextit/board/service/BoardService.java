package kr.or.nextit.board.service;

import java.util.List;

public interface BoardService {

	/**
	 * 보드 글 등록
	 * @param boardVo
	 * @return
	 * @throws Exception
	 */
	public boolean InsertboardItem(
				BoardVo boardVo
			) throws Exception;
	
	/**
	 * Board List 구현
	 * @return
	 * @throws Exception
	 */

	public List<BoardVo> getBoardList(BoardVo boardVo) throws Exception;
	
	public BoardVo getBoardItem(BoardVo boardVo) throws Exception;
	
	public void deleteBoardItem(String seqNo) throws Exception;

	public void updateBoardItem(
			BoardVo params
			) throws Exception;
	
}
