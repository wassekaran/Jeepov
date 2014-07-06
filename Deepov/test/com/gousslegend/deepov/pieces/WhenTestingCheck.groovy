package com.gousslegend.deepov.pieces
import static org.junit.Assert.assertEquals

import org.junit.Test

import spock.lang.*

import com.gousslegend.deepov.Board
import com.gousslegend.deepov.Color
import com.gousslegend.deepov.Position

class WhenTestingCheck extends spock.lang.Specification
{
	@Shared
	def board
	@Shared
	def rook


	def setup()
	{
		board = new Board()
		rook = new Rook()
	}

	def "Testing king position"()
	{
		given:
		def BackKing = new King(position, board, Color.BLACK)
		board.addPiece(BackKing)

		expect:
		position == board.getKing(Color.BLACK).getMyPosition()

		where:
		position << Position.getAllPositionOnBoard()
	}

	def "Testing 2 king positions"()
	{
		given: "2 kings"
		Position BackKingPosition = new Position(0, 0)
		Position WhiteKingPosition = new Position(2, 2)

		King BackKing = new King(BackKingPosition, board, Color.BLACK)
		King WhiteKing = new King(WhiteKingPosition,board, Color.WHITE)

		board.addPiece(BackKing)
		board.addPiece(WhiteKing)

		expect:
		board.getKing(Color.BLACK).getMyPosition()== BackKingPosition
		board.getKing(Color.WHITE).getMyPosition()== WhiteKingPosition
	}

	def "Testing rook checking "()
	{
		given:
		board.addPiece(new Rook(rookPosition, board, Color.WHITE))
		King BackKing = new King(new Position(3, 3), board, Color.BLACK)
		board.addPiece(BackKing)


		expect:
		board.isCheck(Color.BLACK) == check

		where:
		rookPosition 		| check
		new Position(3, 0)  | true
		new Position(3, 5)  | true
		new Position(4, 4)  | false
		new Position(4, 3)  | true
		new Position(7, 4)  | false
		new Position(7, 7)  | false
	}
}